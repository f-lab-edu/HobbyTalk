package com.jongho.openChatRoom.handler;

import com.jongho.common.util.websocket.BaseWebSocketMessage;
import com.jongho.openChatRoom.application.dto.response.OpenChatRoomDto;
import com.jongho.openChatRoom.application.facade.WebSocketOpenChatRoomFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
public class WebSocketOpenChatRoomHandler extends TextWebSocketHandler {
    private final WebSocketOpenChatRoomFacade webSocketOpenChatRoomFacade;
    @Override
    public void afterConnectionEstablished(@NotNull WebSocketSession session){
        try  {
            List<OpenChatRoomDto> openChatRoomDto = webSocketOpenChatRoomFacade.getOpenChatRoomList((long) session.getAttributes().get("userId"));

            session.sendMessage(
                    new TextMessage(BaseWebSocketMessage.join(openChatRoomDto)));
        } catch (Exception e) {
            log.error(e.getMessage());
            handleWebSocketClose(session);
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    }

    private void handleWebSocketClose(WebSocketSession session) {
        try {
            session.close();
        } catch (IOException e) {
            log.error("session.close");
        }
    }
}
