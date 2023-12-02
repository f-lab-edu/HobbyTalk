package com.jongho.user.application.service;

import com.jongho.domain.auth.AuthUser;

import java.util.Optional;

public interface AuthUserService {
    public void authUserCreate(AuthUser authUser);
    public Optional<AuthUser> getAuthUser(Long userId, String userAgent);
    public void updateRefreshToken(AuthUser authUser);
}
