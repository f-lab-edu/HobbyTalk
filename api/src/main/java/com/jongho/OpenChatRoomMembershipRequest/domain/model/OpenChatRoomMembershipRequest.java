package com.jongho.OpenChatRoomMembershipRequest.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OpenChatRoomMembershipRequest {
    private  Long id;
    private final Long requester_id;
    private final Long open_chat_room_id;
    private final String message;
    private final int status;
    public void setId(Long id) {
        this.id = id;
    }
}
