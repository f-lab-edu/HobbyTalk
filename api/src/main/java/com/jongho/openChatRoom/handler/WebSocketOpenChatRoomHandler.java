package com.jongho.openChatRoom.handler;

import com.jongho.common.util.websocket.BaseWebSocketMessage;
import com.jongho.openChatRoom.application.dto.response.OpenChatRoomDto;
import com.jongho.openChatRoom.application.facade.WebSocketOpenChatRoomFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WebSocketOpenChatRoomHandler extends TextWebSocketHandler {
    private final WebSocketOpenChatRoomFacade webSocketOpenChatRoomFacade;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        List<OpenChatRoomDto> openChatRoomDto = webSocketOpenChatRoomFacade.joinOpenChatRoomList((long) session.getAttributes().get("userId"));

        session.sendMessage(
                new TextMessage(BaseWebSocketMessage.join(openChatRoomDto)));
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    }
}
