package com.jongho.openChatRoom.application.service;

import com.jongho.openChat.domain.model.OpenChat;
import com.jongho.openChatRoom.domain.model.redis.RedisOpenChatRoom;
import com.jongho.openChatRoom.domain.model.redis.RedisOpenChatRoomConnectionInfo;
import com.jongho.openChatRoom.domain.repository.OpenChatRoomRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpenChatRoomRedisServiceImpl implements OpenChatRoomRedisService{
    private final OpenChatRoomRedisRepository openChatRoomRedisRepository;
    @Override
    public List<Long> getOpenChatRoomIdList(Long userId) {
        return openChatRoomRedisRepository.getOpenChatRoomIdList(userId);
    }
    @Override
    public void createOpenChatRoomUserList(Long openChatRoomId, List<Long> userIdList) {
        openChatRoomRedisRepository.createOpenChatRoomUserList(openChatRoomId, userIdList);
    }
    @Override
    public void createOpenChatRoomUserList(Long openChatRoomId, Long userId) {
        openChatRoomRedisRepository.createOpenChatRoomUserList(openChatRoomId, userId);
    }
    @Override
    public void createOpenChatRoomLastMessage(Long openChatRoomId, OpenChat openChat) {
        openChatRoomRedisRepository.createOpenChatRoomLastMessage(openChatRoomId, openChat);
    }
    @Override
    public void createRedisOpenChatRoomConnectionInfo(Long userId, Long openChatRoomId, RedisOpenChatRoomConnectionInfo redisOpenChatRoomConnectionInfo) {
        openChatRoomRedisRepository.createRedisOpenChatRoomConnectionInfo(userId, openChatRoomId, redisOpenChatRoomConnectionInfo);
    }
    @Override
    public List<Long> getOpenChatRoomUserList(Long openChatRoomId) {
        return openChatRoomRedisRepository.getOpenChatRoomUserList(openChatRoomId);
    }
    @Override
    public RedisOpenChatRoomConnectionInfo getRedisOpenChatRoomConnectionInfo(Long userId, Long openChatRoomId) {
        return openChatRoomRedisRepository.getRedisOpenChatRoomConnectionInfo(userId, openChatRoomId);
    }
    @Override
    public Optional<OpenChat> getLastOpenChatByChatRoomId(Long chatRoomId){
        return openChatRoomRedisRepository.getLastOpenChatByChatRoomId(chatRoomId);
    };
    @Override
    public Optional<RedisOpenChatRoom> getOpenChatRoom(Long openChatRoomId){
        return openChatRoomRedisRepository.getOpenChatRoom(openChatRoomId);
    };
}
