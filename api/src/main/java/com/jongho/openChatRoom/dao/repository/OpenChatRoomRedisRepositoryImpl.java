package com.jongho.openChatRoom.dao.repository;

import com.jongho.common.util.redis.BaseRedisTemplate;
import com.jongho.common.util.redis.RedisKeyGeneration;
import com.jongho.openChatRoom.domain.repository.OpenChatRoomRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OpenChatRoomRedisRepositoryImpl implements OpenChatRoomRedisRepository {
    private final BaseRedisTemplate baseRedisTemplate;

    @Override
    public List<Long> getOpenChatRoomIdList(Long userId) {
        return baseRedisTemplate.getAllListData(RedisKeyGeneration.getJoinedChatRoomsKey(userId), Long.class);
    }
}
