package com.jongho.openChatRoom.application.service;

import com.jongho.openChatRoom.domain.model.OpenChatRoom;

public interface OpenChatRoomService {
    public void createOpenChatRoom(OpenChatRoom openChatRoom);
    public int getOpenChatRoomCountByManagerId(Long managerId);
    public void incrementOpenChatRoomCurrentAttendance(Long openChatRoomId, int currentAttendance);
}
