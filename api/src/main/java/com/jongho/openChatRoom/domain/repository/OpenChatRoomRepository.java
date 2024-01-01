package com.jongho.openChatRoom.domain.repository;

import com.jongho.openChatRoom.domain.model.OpenChatRoom;

public interface OpenChatRoomRepository {
    public int countByManagerId(Long managerId);
    public void createOpenChatRoom(OpenChatRoom openChatRoom);
    public void updateIncrementCurrentCapacity(Long openChatRoomId);
}
