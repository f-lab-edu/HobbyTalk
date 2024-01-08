package com.jongho.openChatRoom.application.facade;

import com.jongho.category.application.service.CategoryService;
import com.jongho.category.domain.model.Category;
import com.jongho.common.exception.AlreadyExistsException;
import com.jongho.common.exception.CategoryNotFoundException;
import com.jongho.common.exception.MaxChatRoomsExceededException;
import com.jongho.openChatRoom.application.dto.request.OpenChatRoomCreateDto;
import com.jongho.openChatRoom.application.dto.response.MyOpenChatRoomListDto;
import com.jongho.openChatRoom.application.service.OpenChatRoomService;
import com.jongho.openChatRoom.domain.model.OpenChatRoom;
import com.jongho.openChatRoomUser.application.service.OpenChatRoomUserService;
import com.jongho.openChatRoomUser.domain.model.OpenChatRoomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpenChatRoomFacadeImpl implements OpenChatRoomFacade {
    private final OpenChatRoomService openChatRoomService;
    private final OpenChatRoomUserService openChatRoomUserService;
    private final CategoryService categoryService;
    private final int MAXIMUM_OPEN_CHAT_ROOM_COUNT = 5;
    @Override
    @Transactional
    public void createOpenChatRoomAndOpenChatRoomUser(Long authUserId, OpenChatRoomCreateDto openChatRoomCreateDto) {
        if(openChatRoomService.getOpenChatRoomCountByManagerId(authUserId) >= MAXIMUM_OPEN_CHAT_ROOM_COUNT){
            throw new MaxChatRoomsExceededException("최대 개설 가능한 채팅방 개수를 초과하였습니다.");
        }
        Optional<Category> category = categoryService.getOneCategoryById(openChatRoomCreateDto.getCategoryId());
        if(category.isEmpty()){
            throw new CategoryNotFoundException("존재하지 않는 카테고리입니다.");
        }
        Optional<OpenChatRoom> openChatRoomByManagerIdAndTitle = openChatRoomService.getOpenChatRoomByManagerIdAndTitle(authUserId, openChatRoomCreateDto.getTitle());
        if(openChatRoomByManagerIdAndTitle.isPresent()){
            throw new AlreadyExistsException("이미 존재하는 채팅방입니다.");
        }

        OpenChatRoom openChatRoom = new OpenChatRoom(
                openChatRoomCreateDto.getTitle(),
                openChatRoomCreateDto.getNotice(),
                authUserId,
                openChatRoomCreateDto.getCategoryId(),
                openChatRoomCreateDto.getMaximumCapacity(),
                openChatRoomCreateDto.getPassword()
        );
        openChatRoomService.createOpenChatRoom(openChatRoom);
        openChatRoomUserService.createOpenChatRoomUser(new OpenChatRoomUser(openChatRoom.getId(), authUserId));
    }

    @Override
    public List<MyOpenChatRoomListDto> getMyOpenChatRoomList(Long userId, int offset) {
        int DEFAULT_OFFSET = 0;
        if (offset == DEFAULT_OFFSET) return openChatRoomService.getMyOpenChatRoomList(userId);
        return openChatRoomService.getMyOpenChatRoomList(userId, offset);
    }
}
