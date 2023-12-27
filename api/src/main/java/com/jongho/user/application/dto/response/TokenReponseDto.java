package com.jongho.user.application.dto.response;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenReponseDto {
    private final String accessToken;
    private final String refreshToken;
}
