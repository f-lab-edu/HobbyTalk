package com.jongho.openChatRoom.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
public class OpenChatRoomNoticeUpdateDto {
    @NotNull
    private final String notice;

    public OpenChatRoomNoticeUpdateDto(@JsonProperty("notice") String notice) {
        this.notice = notice;
    }
}
