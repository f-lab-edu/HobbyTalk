package com.jongho.user.domain.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class UserNotificationSetting {
    private Long id;
    private final Long userId;
    private final boolean chat;
    private final boolean friendship;
    private final boolean chatRoomMembershipRequest;
}
