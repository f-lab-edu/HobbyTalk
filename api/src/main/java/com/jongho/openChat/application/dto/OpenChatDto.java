package com.jongho.openChat.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jongho.user.domain.model.redis.CachedUserProfile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class OpenChatDto {
    private final Long id;
    private final Long openChatRoomId;
    private final String message;
    private final int type;
    private final int isDeleted;
    private final String deletedTime;
    private final String createdTime;
    private CachedUserProfile senderProfile;

    @JsonCreator
    public OpenChatDto(
            @JsonProperty("id") Long id,
            @JsonProperty("openChatRoomId") Long openChatRoomId,
            @JsonProperty("message") String message,
            @JsonProperty("type") int type,
            @JsonProperty("isDeleted") int isDeleted,
            @JsonProperty("deletedTime") String deletedTime,
            @JsonProperty("createdTime") String createdTime,
            @JsonProperty("senderProfile") CachedUserProfile senderProfile) {
        this.id = id;
        this.openChatRoomId = openChatRoomId;
        this.message = message;
        this.type = type;
        this.isDeleted = isDeleted;
        this.deletedTime = deletedTime;
        this.createdTime = createdTime;
        this.senderProfile = senderProfile;
    }

    public void setSenderProfile(CachedUserProfile senderProfile) {
        this.senderProfile = senderProfile;
    }
}
