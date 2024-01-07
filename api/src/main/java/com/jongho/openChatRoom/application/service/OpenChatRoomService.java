package com.jongho.openChatRoom.application.service;

import com.jongho.openChatRoom.domain.model.OpenChatRoom;

import java.util.Optional;

public interface OpenChatRoomService {
    public void createOpenChatRoom(OpenChatRoom openChatRoom);
    public int getOpenChatRoomCountByManagerId(Long managerId);
    public Optional<OpenChatRoom> getOpenChatRoomByManagerIdAndTitle(Long managerId, String title);
    public void incrementOpenChatRoomCurrentAttendance(Long openChatRoomId, int currentAttendance);
    public Optional<OpenChatRoom> getOpenChatRoomById(Long openChatRoomId);
}
