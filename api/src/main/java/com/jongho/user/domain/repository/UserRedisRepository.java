package com.jongho.user.domain.repository;

import com.jongho.user.domain.model.redis.CachedUserProfile;

public interface UserRedisRepository {
    public CachedUserProfile selectUserProfileByUserId(Long userId);
}
