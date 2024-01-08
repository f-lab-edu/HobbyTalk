package com.jongho.openChatRoom.domain.repository;

import com.jongho.openChatRoom.application.dto.response.MyOpenChatRoomListDto;
import com.jongho.openChatRoom.domain.model.OpenChatRoom;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface OpenChatRoomRepository {
    public int countByManagerId(Long managerId);
    public void createOpenChatRoom(OpenChatRoom openChatRoom);
    public void updateIncrementCurrentCapacity(Long openChatRoomId, int currentAttendance);
    public Optional<OpenChatRoom> selectOneOpenChatRoomByManagerIdAndTitle(Long managerId, String title);
    public List<MyOpenChatRoomListDto> selectMyOpenChatRoomListByUserId(Long userId);
    public List<MyOpenChatRoomListDto> selectMyOpenChatRoomListByUserIdAndOffset(Long userId, int offset);
}
