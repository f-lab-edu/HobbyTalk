package com.jongho.openChatRoom.application.facade;

import com.jongho.openChatRoom.application.dto.request.OpenChatRoomCreateDto;
import com.jongho.openChatRoom.application.dto.response.MyOpenChatRoomListDto;

import java.util.List;

public interface OpenChatRoomFacade {
    public void createOpenChatRoomAndOpenChatRoomUser(Long authUserId, OpenChatRoomCreateDto openChatRoomCreateDto);
    public List<MyOpenChatRoomListDto> getMyOpenChatRoomList(Long userId, int offset);
}
