package com.jongho.openChat.application.facade;

import com.jongho.openChat.application.dto.OpenChatDto;

public interface SendWebSocketOpenChatFacade {
    public void sendOpenChat(OpenChatDto openChatDto);
}
