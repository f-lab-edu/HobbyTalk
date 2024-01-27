package com.jongho.openChat.domain.repository;

import com.jongho.openChat.domain.model.OpenChat;

import java.util.Optional;

public interface OpenChatRedisRepository {
    public Optional<OpenChat> selectLastOpenChatByChatRoomId(Long openChatRoomId);
}
