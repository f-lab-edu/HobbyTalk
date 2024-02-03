package com.jongho.openChat.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OpenChatDto {
    private final Long id;
    private final Long openChatRoomId;
    private final String message;
    private final int type;
    private final int isDeleted;
    private final String deletedTime;
    private final String createdTime;
    private final SenderInfo senderInfo;

    @JsonCreator
    public OpenChatDto(
            @JsonProperty("id") Long id,
            @JsonProperty("senderId") Long senderId,
            @JsonProperty("openChatRoomId") Long openChatRoomId,
            @JsonProperty("message") String message,
            @JsonProperty("type") int type,
            @JsonProperty("isDeleted") int isDeleted,
            @JsonProperty("deletedTime") String deletedTime,
            @JsonProperty("createdTime") String createdTime,
            @JsonProperty("senderInfo") SenderInfo senderInfo) {
        this.id = id;
        this.openChatRoomId = openChatRoomId;
        this.message = message;
        this.type = type;
        this.isDeleted = isDeleted;
        this.deletedTime = deletedTime;
        this.createdTime = createdTime;
        this.senderInfo = senderInfo;
    }
}

@Getter
@ToString
class SenderInfo {
    private final Long senderId;
    private final String nickname;
    private final String profileImage;

    @JsonCreator
    public SenderInfo(
            @JsonProperty("senderId") Long senderId,
            @JsonProperty("nickname") String name,
            @JsonProperty("profileImage") String profileImage) {
        this.senderId = senderId;
        this.nickname = name;
        this.profileImage = profileImage;
    }
}
