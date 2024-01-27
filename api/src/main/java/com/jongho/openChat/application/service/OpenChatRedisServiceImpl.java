package com.jongho.openChat.application.service;

import com.jongho.openChat.domain.model.OpenChat;
import com.jongho.openChat.domain.repository.OpenChatRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpenChatRedisServiceImpl implements OpenChatRedisService {
    private final OpenChatRedisRepository openChatRedisRepository;

    @Override
    public Optional<OpenChat> getLastOpenChatByOpenChatRoomId(Long openChatRoomId){
        return openChatRedisRepository.selectLastOpenChatByChatRoomId(openChatRoomId);
    };
}
