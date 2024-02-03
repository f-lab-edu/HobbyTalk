package com.jongho.user.dao.repository;

import com.jongho.common.util.redis.BaseRedisTemplate;
import com.jongho.common.util.redis.RedisKeyGeneration;
import com.jongho.user.domain.model.redis.CachedUserProfile;
import com.jongho.user.domain.repository.UserRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRedisRepositoryImpl implements UserRedisRepository {
    private final BaseRedisTemplate baseRedisTemplate;
    @Override
    public CachedUserProfile selectUserProfileByUserId(Long userId) {
        return baseRedisTemplate.getHashData(RedisKeyGeneration.getUserProfileKey(userId), CachedUserProfile.class);
    }
}
