package com.jongho.user.application.service;

import com.jongho.user.domain.model.AuthUser;

import java.util.Optional;

public interface AuthUserService {
    public void createAuthUser(AuthUser authUser);
    public Optional<AuthUser> getAuthUser(Long userId);
    public void updateRefreshToken(AuthUser authUser);
}
