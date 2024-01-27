package com.jongho.openChat.domain.repository;

import com.jongho.openChat.domain.model.OpenChat;

import java.util.Optional;

public interface OpenChatRepository {
    public Optional<OpenChat> selectLastOpenChatByChatRoomId(Long openChatRoomId);
}
