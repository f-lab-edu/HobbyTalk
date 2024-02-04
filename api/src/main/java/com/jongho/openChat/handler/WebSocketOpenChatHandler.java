package com.jongho.openChat.handler;

import com.jongho.common.database.redis.RedisService;
import com.jongho.common.util.websocket.BaseMessageTypeEnum;
import com.jongho.common.util.websocket.BaseWebSocketMessage;
import com.jongho.openChat.application.dto.OpenChatDto;
import com.jongho.openChat.application.facade.ReadWebSocketOpenChatFacade;
import com.jongho.openChat.application.facade.ReadWebSocketOpenChatFacadeImpl;
import com.jongho.openChat.application.facade.SendWebSocketOpenChatFacade;
import com.jongho.openChatRoom.application.service.OpenChatRoomRedisService;
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
public class WebSocketOpenChatHandler extends TextWebSocketHandler {
    private final ReadWebSocketOpenChatFacade readWebSocketOpenChatFacade;
    private final SendWebSocketOpenChatFacade sendWebSocketOpenChatFacade;
    private final OpenChatRoomRedisService openChatRoomRedisService;
    private final RedisService redisService;
    private final String OPEN_CHAT_ROOM_CHANNEL = "openChatRoom:";

    /**
     * 웹소켓 연결이 열리고 사용자가 채팅방에 입장할 때 호출
     * @param session 웹소켓 세션
     */
    @Override
    public void afterConnectionEstablished(@NotNull WebSocketSession session){
        try  {
            Long openChatRoomId = (Long) session.getAttributes().get("openChatRoomId");
            Long userId = (Long) session.getAttributes().get("userId");
            List<OpenChatDto> openChatDtoList = readWebSocketOpenChatFacade.getInitialOpenChatList(openChatRoomId);
            initConnectionInfoAndSubscribe(session, openChatRoomId, userId);

            session.sendMessage(
                    new TextMessage(BaseWebSocketMessage.of(BaseMessageTypeEnum.PAGINATION ,openChatDtoList)));
        } catch (Exception e) {
            log.error(e.getMessage());
            handleWebSocketClose(session);
        }
    }
    @Override
    public void handleTextMessage(@NotNull WebSocketSession session, @NotNull TextMessage message) {
        try {
            BaseWebSocketMessage<OpenChatDto> baseWebSocketMessage = redisService.convertStringMessageToBaseWebSocketMessage(message);

            switch (baseWebSocketMessage.getType().getValue()) {
                case "SEND" -> createOpenChatAndSendMessage(baseWebSocketMessage.getData());
                case "PAGINATION" -> readWebSocketOpenChatFacade.getOpenChatListByOpenChatRoomIdAndLastCreatedTime(baseWebSocketMessage.getData().getOpenChatRoomId(), baseWebSocketMessage.getData().getCreatedTime());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            handleWebSocketClose(session);
        }
    }
    private void handleWebSocketClose(WebSocketSession session) {
        try {
            session.close();
        } catch (IOException e) {
            log.error("session.close");
        }
    }

    /**
     * 채팅장 입장 시 초기화 작업 및 채팅방 구독
     * @param session 웹소켓 세션
     * @param openChatRoomId 채팅방 아이디
     * @param userId 사용자 아이디
     */
    private void initConnectionInfoAndSubscribe(WebSocketSession session, Long openChatRoomId, Long userId) {
        openChatRoomRedisService.updateInitUnreadChatCount(userId, openChatRoomId);
        openChatRoomRedisService.updateActiveChatRoom(userId, openChatRoomId);
        redisService.subscribe(OPEN_CHAT_ROOM_CHANNEL + openChatRoomId, session);
    }

    private void createOpenChatAndSendMessage(OpenChatDto openChatDto) {
        sendWebSocketOpenChatFacade.sendOpenChat(openChatDto);
        redisService.publish(
                OPEN_CHAT_ROOM_CHANNEL + openChatDto.getOpenChatRoomId(),
                BaseWebSocketMessage.of(BaseMessageTypeEnum.SEND, openChatDto));
    }
}
