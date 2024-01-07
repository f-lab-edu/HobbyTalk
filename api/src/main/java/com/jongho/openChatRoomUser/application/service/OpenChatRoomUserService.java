package com.jongho.openChatRoomUser.application.service;

import com.jongho.openChatRoomUser.domain.model.OpenChatRoomUser;

import java.util.Optional;

public interface OpenChatRoomUserService {
    public void createOpenChatRoomUser(OpenChatRoomUser openChatRoomUser);
    public Optional<OpenChatRoomUser> getOpenChatRoomUserByOpenChatRoomIdAndUserId(Long openChatRoomId, Long userId);
}
