package com.jongho.openChat.dao.repository;

import com.jongho.common.util.redis.BaseRedisTemplate;
import com.jongho.common.util.redis.RedisKeyGeneration;
import com.jongho.openChat.domain.model.OpenChat;
import com.jongho.openChat.domain.repository.OpenChatRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OpenChatRedisRepositoryImpl implements OpenChatRedisRepository {
    private final BaseRedisTemplate baseRedisTemplate;

    @Override
    public Optional<OpenChat> selectLastOpenChatByChatRoomId(Long openChatRoomId){
        return Optional.ofNullable(baseRedisTemplate.getData(
                RedisKeyGeneration.getLastMessageKey(openChatRoomId),
                OpenChat.class));
    }
}
