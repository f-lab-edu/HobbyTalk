package com.jongho.openChatRoomUser.dao.mapper;

import com.jongho.openChatRoomUser.domain.model.OpenChatRoomUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OpenChatRoomUserMapper {
    void createOpenChatRoomUser(OpenChatRoomUser openChatRoomUser);
    OpenChatRoomUser selectOneOpenChatRoomUserByOpenChatRoomIdAndUserId(Long openChatRoomId, Long userId);
}
