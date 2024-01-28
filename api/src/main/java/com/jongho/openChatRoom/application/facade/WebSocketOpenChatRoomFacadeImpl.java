package com.jongho.openChatRoom.application.facade;

import com.jongho.common.exception.OpenChatRoomNotFoundException;
import com.jongho.common.util.date.DateUtil;
import com.jongho.openChat.application.service.OpenChatRedisService;
import com.jongho.openChat.application.service.OpenChatService;
import com.jongho.openChat.domain.model.OpenChat;
import com.jongho.openChatRoom.application.dto.response.OpenChatRoomDto;
import com.jongho.openChatRoom.application.service.OpenChatRoomRedisService;
import com.jongho.openChatRoom.application.service.OpenChatRoomService;
import com.jongho.openChatRoom.domain.model.redis.RedisOpenChatRoom;
import com.jongho.openChatRoom.domain.model.redis.RedisOpenChatRoomConnectionInfo;
import com.jongho.openChatRoomUser.application.service.OpenChatRoomUserService;
import com.jongho.openChatRoomUser.domain.model.OpenChatRoomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebSocketOpenChatRoomFacadeImpl implements WebSocketOpenChatRoomFacade{
    private final OpenChatRoomRedisService openChatRoomRedisService;
    private final OpenChatRoomService openChatRoomService;
    private final OpenChatService openChatService;
    private final OpenChatRedisService openChatRedisService;
    private final OpenChatRoomUserService openChatRoomUserService;
    public List<OpenChatRoomDto> joinOpenChatRoomList(Long userId){
        List<Long> openChatRoomIdList = openChatRoomRedisService.getOpenChatRoomIdList(userId);
        List<OpenChatRoomDto> openChatRoomDtoList;
        if (openChatRoomIdList.size() == 0){
            List<RedisOpenChatRoom> openChatRoomList = openChatRoomService.getJoinOpenChatRoomList(userId);
            openChatRoomDtoList = enrichOpenChatRoomDtoListWithLastOpenChat(userId, openChatRoomList);
        }else{
            List<RedisOpenChatRoom> openChatRoomList = new ArrayList<>();
            for (Long openChatRoomId : openChatRoomIdList){
                RedisOpenChatRoom redisOpenChatRoom = getRedisOpenChatRoom(openChatRoomId);
                if (redisOpenChatRoom == null){
                    continue;
                }
                openChatRoomList.add(redisOpenChatRoom);
            }
            openChatRoomDtoList = enrichOpenChatRoomDtoListWithLastOpenChat(userId, openChatRoomList);
        }

        return sortOpenChatRoomDtoListByLastOpenChatCreatedTime(openChatRoomDtoList);
    }

    private List<OpenChatRoomDto> enrichOpenChatRoomDtoListWithLastOpenChat(Long userId, List<RedisOpenChatRoom> openChatRoomList) {
        return openChatRoomList
                .stream()
                .map(OpenChatRoomDto::new)
                .peek(openChatRoomDto -> {
                    openChatRoomDto.setOpenChat(getLastOpenChatByOpenChatRoomId(openChatRoomDto));
                    openChatRoomDto.setRedisOpenChatRoomConnectionInfo(getRedisOpenChatRoomConnectionInfo(userId, openChatRoomDto));
                })
                .toList();

    }

    public RedisOpenChatRoom getRedisOpenChatRoom(Long openChatRoomId){
        Optional<RedisOpenChatRoom> redisOpenChatRoom = openChatRoomRedisService.getOpenChatRoom(openChatRoomId);
        if (redisOpenChatRoom.isEmpty()){
            Optional<RedisOpenChatRoom> openChatRoom = openChatRoomService.getRedisOpenChatRoomById(openChatRoomId);
            if(openChatRoom.isEmpty()){
                System.out.println("존재하지 않는 채팅방 id:" + openChatRoomId);
                return null;
            }
            redisOpenChatRoom = openChatRoom;
        }

        return redisOpenChatRoom.get();
    }

    private OpenChat getLastOpenChatByOpenChatRoomId(OpenChatRoomDto openChatRoomDto){
        Optional<OpenChat> openChat = openChatRedisService.getLastOpenChatByOpenChatRoomId(openChatRoomDto.getId());
        if(openChat.isEmpty()){
            OpenChat lastOpenChat = openChatService.getLastOpenChatByOpenChatRoomId(openChatRoomDto.getId())
                    .orElse(null);
            if(lastOpenChat != null){
                openChatRoomRedisService.createOpenChatRoomLastMessage(openChatRoomDto.getId(), lastOpenChat);
            }

            return lastOpenChat;
        }

        return openChat.get();
    }

    private List<Long> getOpenChatRoomUserList(RedisOpenChatRoom openChatRoom){
        List<Long> idList = openChatRoomRedisService.getOpenChatRoomIdList(openChatRoom.getId());
        if (idList.size() == 0 || idList.size() != openChatRoom.getCurrentAttendance()){
            idList = openChatRoomService.getOpenChatRoomUserList(openChatRoom.getId());
            openChatRoomRedisService.createOpenChatRoomUserList(openChatRoom.getId(), idList);
        }

        return idList;
    }

    private RedisOpenChatRoomConnectionInfo getRedisOpenChatRoomConnectionInfo(Long userId, OpenChatRoomDto openChatRoomDto){
        RedisOpenChatRoomConnectionInfo redisOpenChatRoomConnectionInfo = openChatRoomRedisService.getRedisOpenChatRoomConnectionInfo(userId, openChatRoomDto.getId());
        if(redisOpenChatRoomConnectionInfo == null){
            redisOpenChatRoomConnectionInfo = new RedisOpenChatRoomConnectionInfo();
            OpenChatRoomUser openChatRoomUser = openChatRoomUserService.getOpenChatRoomUserByOpenChatRoomIdAndUserId(openChatRoomDto.getId(), userId)
                    .orElseThrow(() -> new OpenChatRoomNotFoundException("채팅방에 참여하지 않은 유저입니다."));

            redisOpenChatRoomConnectionInfo.setLastExitTime(openChatRoomUser.getLastExitTime());
            redisOpenChatRoomConnectionInfo.setActive(0);
            redisOpenChatRoomConnectionInfo.setUnReadMessageCount(getUnReadMessageCount(openChatRoomDto, openChatRoomUser.getLastExitTime()));

            openChatRoomRedisService.createRedisOpenChatRoomConnectionInfo(userId, openChatRoomDto.getId(), redisOpenChatRoomConnectionInfo);
        }

        return redisOpenChatRoomConnectionInfo;
    }

    private int getUnReadMessageCount(OpenChatRoomDto openChatRoomDto, String lastExitTime){
        int count = 0;
        int maxCount = 200;
        List<OpenChat> openChatList = openChatRedisService.getOpenChatListByOpenChatRoomId(openChatRoomDto.getId());
        LocalDate lastExitDate = DateUtil.convertStringToDate(lastExitTime);
        for (OpenChat openChat : openChatList){
            if (DateUtil.convertStringToDate(openChat.getCreatedTime()).isAfter(lastExitDate)){
                count++;
            } else if(DateUtil.convertStringToDate(openChat.getCreatedTime()).isBefore(lastExitDate) || count >= maxCount){
                return count;
            }
        }

        count += openChatService.getUnReadOpenChatCountByOpenChatRoomIdAndLastExitTime(openChatRoomDto.getId(), lastExitTime);

        return count;
    }

    private List<OpenChatRoomDto> sortOpenChatRoomDtoListByLastOpenChatCreatedTime(List<OpenChatRoomDto> myOpenChatRoomDtoList) {
        return myOpenChatRoomDtoList.stream()
                .sorted(
                        Comparator.comparing(
                                openChatRoomDto -> {
                                    OpenChat openChat = openChatRoomDto.getLastChat();
                                    return openChat != null ? openChat.getCreatedTime() : openChatRoomDto.getCreatedTime();
                                },
                                Comparator.reverseOrder()))
                .toList();
    }
}
