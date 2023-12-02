package com.jongho.util.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RefreshPayload {
    private final Long userId;
    private final int isAccessToken = 0;
}
