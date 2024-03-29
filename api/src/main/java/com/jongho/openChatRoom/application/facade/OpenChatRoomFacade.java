package com.jongho.openChatRoom.application.facade;

import com.jongho.openChatRoom.application.dto.request.OpenChatRoomCreateDto;

public interface OpenChatRoomFacade {
    public void createOpenChatRoomAndOpenChatRoomUser(Long authUserId, OpenChatRoomCreateDto openChatRoomCreateDto);
    public void joinOpenChatRoom(Long authUserId, Long openChatRoomId, String password);
    public void createOpenChatRoomMembershipRequest(Long authUserId, Long openChatRoomId, String message);
}
