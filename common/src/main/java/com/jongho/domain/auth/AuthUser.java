package com.jongho.domain.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthUser {
    private final Long id;
    private final String username;
}
