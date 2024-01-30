package com.jongho.common.util.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jongho.common.exception.MyJsonProcessingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class BaseWebSocketMessage<T> {
    private final BaseMessageTypeEnum type;
    private final T data;
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String of(T data) {
        try {
            return objectMapper.writeValueAsString(new BaseWebSocketMessage<T>(BaseMessageTypeEnum.JOIN, data));
        }catch (Exception e) {
            throw new MyJsonProcessingException(e.getMessage()!=null? e.getMessage():"json processing error");
        }
    }
}
