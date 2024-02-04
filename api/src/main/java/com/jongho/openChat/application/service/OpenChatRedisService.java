package com.jongho.openChat.application.service;

import com.jongho.openChat.domain.model.OpenChat;

import java.util.List;
import java.util.Optional;

public interface OpenChatRedisService {
    public Optional<OpenChat> getLastOpenChatByOpenChatRoomId(Long openChatRoomId);
    public List<OpenChat> getOpenChatListByOpenChatRoomId(Long openChatRoomId);
    public List<OpenChat> getOpenChatListByOpenChatRoomIdAndOffsetAndLimit(Long openChatRoomId, int offset, int limit);
    public void createOpenChat(OpenChat openChat);
    public void updateLastOpenChat(OpenChat openChat);
}
