package com.jongho.openChatRoom.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OpenChatRoomMapper {
    public int countByManagerId();
}
