package com.jongho.openChat.application.service;

import com.jongho.openChat.domain.model.OpenChat;

import java.util.Optional;

public interface OpenChatService {
    public Optional<OpenChat> getLastOpenChatByOpenChatRoomId(Long openChatRoomId);
}
