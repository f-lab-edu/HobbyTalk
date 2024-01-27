package com.jongho.openChatRoom.application.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jongho.openChat.domain.OpenChat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class OpenChatRoomDto {
    private final Long id;
    private final String title;
    private final String notice;
    private final Long managerId;
    private final Long categoryId;
    private final int maximumCapacity;
    private final int currentAttendance;
    private final String createdTime;
    private OpenChat openChat;
    private List<Long> openChatRoomUserIds;

    @JsonCreator
    public OpenChatRoomDto(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title,
            @JsonProperty("notice") String notice,
            @JsonProperty("managerId") Long managerId,
            @JsonProperty("categoryId") Long categoryId,
            @JsonProperty("maximumCapacity") int maximumCapacity,
            @JsonProperty("currentAttendance") int currentAttendance,
            @JsonProperty("createdTime") String createdTime) {
        this.id = id;
        this.title = title;
        this.notice = notice;
        this.managerId = managerId;
        this.categoryId = categoryId;
        this.maximumCapacity = maximumCapacity;
        this.currentAttendance = currentAttendance;
        this.createdTime = createdTime;
    }

    @JsonCreator
    public OpenChatRoomDto(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title,
            @JsonProperty("notice") String notice,
            @JsonProperty("managerId") Long managerId,
            @JsonProperty("categoryId") Long categoryId,
            @JsonProperty("maximumCapacity") int maximumCapacity,
            @JsonProperty("currentAttendance") int currentAttendance,
            @JsonProperty("createdTime") String createdTime,
            @JsonProperty("openChat") OpenChat openChat,
            @JsonProperty("openChatRoomUserIds") List<Long> openChatRoomUserIds) {
        this.id = id;
        this.title = title;
        this.notice = notice;
        this.managerId = managerId;
        this.categoryId = categoryId;
        this.maximumCapacity = maximumCapacity;
        this.currentAttendance = currentAttendance;
        this.createdTime = createdTime;
        this.openChat = openChat;
        this.openChatRoomUserIds = openChatRoomUserIds;
    }

    public void setOpenChat(OpenChat openChat) {
        this.openChat = openChat;
    }

    public void setOpenChatRoomUserIds(List<Long> openChatRoomUserIds) {
        this.openChatRoomUserIds = openChatRoomUserIds;
    }
}
