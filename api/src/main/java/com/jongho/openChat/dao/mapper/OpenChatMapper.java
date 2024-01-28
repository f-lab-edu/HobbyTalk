package com.jongho.openChat.dao.mapper;

import com.jongho.openChat.domain.model.OpenChat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OpenChatMapper {
    public OpenChat selectLastOpenChatByChatRoomId(Long openChatRoomId);
    public int selectUnReadOpenChatCountByChatRoomIdAndLastExitTime(
            @Param("openChatRoomId") Long openChatRoomId,
            @Param("lastExitTime") String lastExitTime);
}
