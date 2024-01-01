package com.jongho.openChatRoomUser.application.service;

import com.jongho.openChatRoomUser.domain.model.OpenChatRoomUser;
import com.jongho.openChatRoomUser.domain.repository.OpenChatRoomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenChatRoomUserServiceImpl implements OpenChatRoomUserService{
    private final OpenChatRoomUserRepository openChatRoomUserRepository;
    @Override
    public void createOpenChatRoomUser(OpenChatRoomUser openChatRoomUser) {
        openChatRoomUserRepository.createOpenChatRoomUser(openChatRoomUser);
    }
}
