package com.jongho.openChatRoom.application.service;

import com.jongho.openChatRoom.application.dto.response.MyOpenChatRoomListDto;
import com.jongho.openChatRoom.domain.model.OpenChatRoom;

import java.util.List;
import java.util.Optional;

public interface OpenChatRoomService {
    public void createOpenChatRoom(OpenChatRoom openChatRoom);
    public int getOpenChatRoomCountByManagerId(Long managerId);
    public Optional<OpenChatRoom> getOpenChatRoomByManagerIdAndTitle(Long managerId, String title);
    public void incrementOpenChatRoomCurrentAttendance(Long openChatRoomId, int currentAttendance);
    public List<MyOpenChatRoomListDto> getMyOpenChatRoomList(Long userId);
    public List<MyOpenChatRoomListDto> getMyOpenChatRoomList(Long userId, int offset);
}
