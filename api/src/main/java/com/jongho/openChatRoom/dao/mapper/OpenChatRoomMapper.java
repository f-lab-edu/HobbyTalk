package com.jongho.openChatRoom.dao.mapper;

import com.jongho.openChatRoom.application.dto.response.OpenChatRoomDto;
import com.jongho.openChatRoom.domain.model.OpenChatRoom;
import com.jongho.openChatRoom.domain.model.redis.RedisOpenChatRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OpenChatRoomMapper {
    public int countByManagerId(Long managerId);
    public void createOpenChatRoom(OpenChatRoom openChatRoom);
    public void updateIncrementCurrentCapacity(@Param("id") Long id, @Param("currentAttendance") int currentAttendance);
    public OpenChatRoom selectOneOpenChatRoomByIdForUpdate(@Param("id") Long id);
    public OpenChatRoom selectOneOpenChatRoomById(@Param("id") Long id);
    public void updateOpenChatRoomNotice(@Param("id") Long id, @Param("notice") String notice);
    public OpenChatRoom selectOneOpenChatRoomByManagerIdAndTitle(@Param("managerId") Long managerId, @Param("title") String title);
    public List<RedisOpenChatRoom> selectJoinOpenChatRoomByUserId(Long userId);
    public List<Long> selectOpenChatRoomUser(Long openChatRoomId);
    public RedisOpenChatRoom selectRedisOpenChatRoomById(Long openChatRoomId);
}
