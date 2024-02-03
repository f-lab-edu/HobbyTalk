package com.jongho.openChat.dao.mapper;

import com.jongho.openChat.application.dto.OpenChatDto;
import com.jongho.openChat.domain.model.OpenChat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OpenChatMapper {
    public OpenChat selectLastOpenChatByChatRoomId(Long openChatRoomId);
    public int selectUnReadOpenChatCountByChatRoomIdAndLastExitTime(
            @Param("openChatRoomId") Long openChatRoomId,
            @Param("lastExitTime") String lastExitTime,
            @Param("limit") int limit);
    public List<OpenChatDto> selectOpenChatCountByChatRoomIdAndLastExitTime(
            @Param("openChatRoomId") Long openChatRoomId,
            @Param("lastExitTime") String lastExitTime,
            @Param("limit") int limit);
}
