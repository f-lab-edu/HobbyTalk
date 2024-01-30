package com.jongho.openChatRoom.application.facade;

import com.jongho.openChatRoom.application.dto.response.OpenChatRoomDto;

import java.util.List;

public interface WebSocketOpenChatRoomFacade {
    public List<OpenChatRoomDto> getOpenChatRoomList(Long userId);
}
