package com.jongho.user.application.service;

import com.jongho.user.domain.model.redis.CachedUserProfile;

public interface UserRedisService {
    public CachedUserProfile getUserProfileByUserId(Long userId);
    public void createUserProfileByUserId(Long userId, String nickname, String profileImage);
}
