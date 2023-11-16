package com.jongho.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserNotificationSetting {
    private Long id;
    private final Long userId;
    private final boolean chat;
    private final boolean friendShip;
}
