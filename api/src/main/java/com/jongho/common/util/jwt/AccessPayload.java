package com.jongho.common.util.jwt;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class AccessPayload {
    private final Long userId;
    private final String username;
    private final TokenType tokenType = TokenType.ACCESS_TOKEN;
}
