package com.jongho.user.application.facade;

import com.jongho.user.application.dto.response.TokenResponseDto;


public interface AuthUserFacade {
    public TokenResponseDto signIn(String username, String password);
    public TokenResponseDto tokenRefresh(String refreshToken);
}
