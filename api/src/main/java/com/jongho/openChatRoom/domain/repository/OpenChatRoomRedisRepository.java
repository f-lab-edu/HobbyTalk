package com.jongho.openChatRoom.domain.repository;

import com.jongho.openChat.domain.model.OpenChat;
import com.jongho.openChatRoom.domain.model.redis.RedisOpenChatRoom;
import com.jongho.openChatRoom.domain.model.redis.RedisOpenChatRoomConnectionInfo;

import java.util.List;
import java.util.Optional;

public interface OpenChatRoomRedisRepository {
    public List<Long> getOpenChatRoomIdList(Long userId);
    public void createOpenChatRoomUserList(Long openChatRoomId, List<Long> userIdList);
    public void createOpenChatRoomUserList(Long openChatRoomId, Long userId);
    public void createOpenChatRoomLastMessage(Long openChatRoomId, OpenChat openChat);
    public void createRedisOpenChatRoomConnectionInfo(Long userId, Long openChatRoomId, RedisOpenChatRoomConnectionInfo redisOpenChatRoomConnectionInfo);
    public Optional<OpenChat> getLastOpenChatByChatRoomId(Long chatRoomId);
    public List<Long> getOpenChatRoomUserList(Long openChatRoomId);
    public RedisOpenChatRoomConnectionInfo getRedisOpenChatRoomConnectionInfo(Long userId, Long openChatRoomId);
    public Optional<RedisOpenChatRoom> getOpenChatRoom(Long openChatRoomId);
}
