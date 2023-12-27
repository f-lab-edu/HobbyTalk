package com.jongho.user.application.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class TokenRefreshDto {
    private String refreshToken;

}
