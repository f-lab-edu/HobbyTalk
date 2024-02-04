package com.jongho.common.database.redis;

import com.jongho.common.exception.MyJsonProcessingException;
import com.jongho.common.util.websocket.BaseWebSocketMessage;
import com.jongho.openChat.application.dto.OpenChatDto;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public interface RedisService {
    public void publish(String channel, String message);
    public void subscribe(String channel, WebSocketSession session);
    public void subscribe(List<String> channel, WebSocketSession session);
    public BaseWebSocketMessage<OpenChatDto> convertStringMessageToBaseWebSocketMessage(TextMessage message);
}
