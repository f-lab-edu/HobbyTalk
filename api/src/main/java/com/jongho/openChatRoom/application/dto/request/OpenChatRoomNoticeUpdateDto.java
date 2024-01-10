package com.jongho.openChatRoom.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class OpenChatRoomNoticeUpdateDto {
    @NotNull
    private final String notice;
}
