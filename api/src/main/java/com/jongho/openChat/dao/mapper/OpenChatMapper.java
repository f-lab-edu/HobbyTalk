package com.jongho.openChat.dao.mapper;

import com.jongho.openChat.domain.model.OpenChat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OpenChatMapper {
    public OpenChat selectLastOpenChatByChatRoomId(Long openChatRoomId);
}
