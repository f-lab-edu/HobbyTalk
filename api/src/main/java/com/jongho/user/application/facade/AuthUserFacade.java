package com.jongho.user.application.facade;

import com.jongho.user.application.dto.response.TokenReponseDto;

import java.util.Map;

public interface AuthUserFacade {
    public TokenReponseDto signIn(String username, String password);
    public TokenReponseDto tokenRefresh(String refreshToken);
}
