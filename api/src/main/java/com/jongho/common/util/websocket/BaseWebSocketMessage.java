package com.jongho.common.util.websocket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jongho.common.exception.MyJsonProcessingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
public class BaseWebSocketMessage<T> {
    private final BaseMessageTypeEnum type;
    private final T data;
    public static ObjectMapper objectMapper = new ObjectMapper();
    @JsonCreator
    public BaseWebSocketMessage(
            @JsonProperty("type") BaseMessageTypeEnum type,
            @JsonProperty("data") T data) {
        this.type = type;
        this.data = data;
    }

    public static <T> String of(BaseMessageTypeEnum baseMessageTypeEnum, T data) {
        try {
            return objectMapper.writeValueAsString(new BaseWebSocketMessage<>(baseMessageTypeEnum, data));
        }catch (Exception e) {
            throw new MyJsonProcessingException(e.getMessage()!=null? e.getMessage():"json processing error");
        }
    }
}
