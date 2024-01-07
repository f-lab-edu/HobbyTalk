package com.jongho.openChatRoomUser.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class OpenChatRoomUser {
    private final Long openChatRoomId;
    private final Long userId;
}
