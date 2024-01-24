package com.jongho.common.config;

import com.jongho.common.interceptor.WebSocketAuthInterceptor;
import com.jongho.openChat.handler.WebSocketOpenChatHandler;
import com.jongho.openChatRoom.handler.WebSocketOpenChatRoomHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketAuthInterceptor webSocketAuthInterceptor;
    private final WebSocketOpenChatHandler webSocketOpenChatHandler;
    private final WebSocketOpenChatRoomHandler webSocketOpenChatRoomHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketOpenChatRoomHandler, "/openChatRoom")
                .addHandler(webSocketOpenChatHandler, "/openChat")
                .addInterceptors(webSocketAuthInterceptor)
                .setAllowedOrigins("*");
    }
}
