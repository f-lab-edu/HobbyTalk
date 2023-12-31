package com.jongho.user.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class AuthUser {
    private final Long userId;
    private final String refreshToken;
}
