package com.jongho.common.database.redis;

import com.jongho.common.util.websocket.BaseWebSocketMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public interface RedisService {
    public void publish(String channel, String message);
    public void subscribe(String channel, WebSocketSession session);
    public void subscribe(List<String> channel, WebSocketSession session);
    public BaseWebSocketMessage convertStringMessageToBaseWebSocketMessage(TextMessage message);
    public <T> T dataToObject(String data, Class<T> valueType);
    public String objectToData(Object object);
}
