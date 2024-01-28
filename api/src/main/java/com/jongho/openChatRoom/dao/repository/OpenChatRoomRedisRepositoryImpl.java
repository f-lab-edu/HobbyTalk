package com.jongho.openChatRoom.dao.repository;

import com.jongho.common.util.redis.BaseRedisTemplate;
import com.jongho.common.util.redis.RedisKeyGeneration;
import com.jongho.openChat.domain.model.OpenChat;
import com.jongho.openChatRoom.domain.model.redis.RedisOpenChatRoom;
import com.jongho.openChatRoom.domain.model.redis.RedisOpenChatRoomConnectionInfo;
import com.jongho.openChatRoom.domain.repository.OpenChatRoomRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OpenChatRoomRedisRepositoryImpl implements OpenChatRoomRedisRepository {
    private final BaseRedisTemplate baseRedisTemplate;

    @Override
    public List<Long> getOpenChatRoomIdList(Long userId) {
        return baseRedisTemplate.getAllListData(RedisKeyGeneration.getJoinedChatRoomsKey(userId), Long.class);
    }

    @Override
    public void createOpenChatRoomUserList(Long openChatRoomId, List<Long> userIdList) {
        baseRedisTemplate.setAllListData(RedisKeyGeneration.getChatRoomUserListKey(openChatRoomId), userIdList);
    }

    @Override
    public void createOpenChatRoomUserList(Long openChatRoomId, Long userId) {
        baseRedisTemplate.setListData(RedisKeyGeneration.getChatRoomUserListKey(openChatRoomId), userId);
    }

    @Override
    public void createOpenChatRoomLastMessage(Long openChatRoomId, OpenChat openChat) {
        baseRedisTemplate.setListData(RedisKeyGeneration.getLastMessageKey(openChatRoomId), openChat);
    }

    @Override
    public void createRedisOpenChatRoomConnectionInfo(Long userId, Long openChatRoomId, RedisOpenChatRoomConnectionInfo redisOpenChatRoomConnectionInfo) {
        baseRedisTemplate.setHashData(RedisKeyGeneration.getChatRoomConnectionInfoKey(userId, openChatRoomId), redisOpenChatRoomConnectionInfo.toMap());
    }

    @Override
    public List<Long> getOpenChatRoomUserList(Long openChatRoomId) {
        return baseRedisTemplate.getAllListData(RedisKeyGeneration.getChatRoomUserListKey(openChatRoomId), Long.class);
    }

    @Override
    public RedisOpenChatRoomConnectionInfo getRedisOpenChatRoomConnectionInfo(Long userId, Long openChatRoomId) {
        return baseRedisTemplate.getHashData(RedisKeyGeneration.getChatRoomConnectionInfoKey(userId, openChatRoomId), RedisOpenChatRoomConnectionInfo.class);
    }

    @Override
    public Optional<OpenChat> getLastOpenChatByChatRoomId(Long chatRoomId){
        return Optional.ofNullable(
                baseRedisTemplate.getData(RedisKeyGeneration.getLastMessageKey(chatRoomId), OpenChat.class));
    }

    @Override
    public Optional<RedisOpenChatRoom> getOpenChatRoom(Long openChatRoomId){
        return Optional.ofNullable(
                baseRedisTemplate.getData(RedisKeyGeneration.getChatRoomKey(openChatRoomId), RedisOpenChatRoom.class));
    }
}
