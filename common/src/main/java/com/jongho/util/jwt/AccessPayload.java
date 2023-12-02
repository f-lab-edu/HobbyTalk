package com.jongho.util.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AccessPayload {
    private final Long userId;
    private final String username;
    private final int isAccessToken = 1;
}
