package com.jongho.util.jwt;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class RefreshPayload {
    private final Long userId;
    private final int isAccessToken = 0;
}
