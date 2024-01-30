package com.jongho.common.database.redis;

import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public interface RedisService {
    public void publish(String channel, String message);
    public void subscribe(String channel, WebSocketSession session);
    public void subscribe(List<String> channel, WebSocketSession session);
}
