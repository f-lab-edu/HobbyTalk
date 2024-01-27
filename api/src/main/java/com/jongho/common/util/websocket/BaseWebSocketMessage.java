package com.jongho.common.util.websocket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class BaseWebSocketMessage<T> {
    private final BaseMessageTypeEnum type;
    private final T data;

    public static <T> BaseWebSocketMessage<T> join(T data) {
        return new BaseWebSocketMessage<T>(BaseMessageTypeEnum.JOIN, data);
    }
}
