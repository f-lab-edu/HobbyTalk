package com.jongho.openChatRoom.application.service;

import com.jongho.openChatRoom.domain.model.OpenChatRoom;

public interface OpenChatRoomService {
    public void createOpenChatRoom(OpenChatRoom openChatRoom);
    public int getCountByManagerId(Long managerId);
    public void updateIncrementCurrentCapacity(Long openChatRoomId);
}
