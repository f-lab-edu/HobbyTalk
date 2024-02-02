package com.jongho.openChatRoomUser.domain.model;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OpenChatRoomUser {
    private final Long openChatRoomId;
    private final Long userId;
    private String LastExitTime;
}
