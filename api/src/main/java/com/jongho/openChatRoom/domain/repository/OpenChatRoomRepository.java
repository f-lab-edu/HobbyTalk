package com.jongho.openChatRoom.domain.repository;

import com.jongho.openChatRoom.application.dto.response.OpenChatRoomDto;
import com.jongho.openChatRoom.domain.model.OpenChatRoom;

import java.util.List;
import java.util.Optional;

public interface OpenChatRoomRepository {
    public int countByManagerId(Long managerId);
    public void createOpenChatRoom(OpenChatRoom openChatRoom);
    public void updateIncrementCurrentCapacity(Long openChatRoomId, int currentAttendance);
    public Optional<OpenChatRoom> selectOneOpenChatRoomByManagerIdAndTitle(Long managerId, String title);
    public Optional<OpenChatRoom> selectOneOpenChatRoomByIdForUpdate(Long openChatRoomId);
    public Optional<OpenChatRoom> selectOneOpenChatRoomById(Long openChatRoomId);
    public void updateOpenChatRoomNotice(Long openChatRoomId, String notice);
    public List<OpenChatRoomDto> selectJoinOpenChatRoomByUserId(Long userId);
    public List<Long> selectOpenChatRoomUser(Long openChatRoomId);
}
