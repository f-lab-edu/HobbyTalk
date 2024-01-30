package com.jongho.openChat.application.service;

import com.jongho.openChat.domain.model.OpenChat;

import java.util.List;
import java.util.Optional;

public interface OpenChatService {
    public Optional<OpenChat> getLastOpenChatByOpenChatRoomId(Long openChatRoomId);
    public int getUnReadOpenChatCountByOpenChatRoomIdAndLastExitTime(Long openChatRoomId, String lastExitTime, int limit);
}
