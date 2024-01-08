package com.jongho.openChatRoom.dao.mapper;

import com.jongho.openChatRoom.application.dto.response.MyOpenChatRoomListDto;
import com.jongho.openChatRoom.domain.model.OpenChatRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OpenChatRoomMapper {
    public int countByManagerId(Long managerId);
    public void createOpenChatRoom(OpenChatRoom openChatRoom);
    public void updateIncrementCurrentCapacity(@Param("id") Long id, @Param("currentAttendance") int currentAttendance);
    public OpenChatRoom selectOneOpenChatRoomById(@Param("id") Long id);
    public OpenChatRoom selectOneOpenChatRoomByManagerIdAndTitle(@Param("managerId") Long managerId, @Param("title") String title);
    public List<MyOpenChatRoomListDto> selectMyOpenChatRoomListByUserId(@Param("userId") Long userId);
    public List<MyOpenChatRoomListDto> selectMyOpenChatRoomListByUserIdAndOffset(@Param("userId") Long userId, @Param("offset") int offset);
}
