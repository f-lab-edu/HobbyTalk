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
public class BaseWebSocketMessage {
    private final BaseMessageTypeEnum type;
    private final String data;
    public static ObjectMapper objectMapper = new ObjectMapper();
    @JsonCreator
    public BaseWebSocketMessage(
            @JsonProperty("type") BaseMessageTypeEnum type,
            @JsonProperty("data") String data) {
        this.type = type;
        this.data = data;
    }

    public static <T> String of(BaseMessageTypeEnum baseMessageTypeEnum, T data) {
        try {
            String stringData = objectMapper.writeValueAsString(data);
            return objectMapper.writeValueAsString(new BaseWebSocketMessage(baseMessageTypeEnum, stringData));
        }catch (Exception e) {
            throw new MyJsonProcessingException(e.getMessage()!=null? e.getMessage():"json processing error");
        }
    }
}
