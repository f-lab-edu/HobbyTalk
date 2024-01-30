package com.jongho.openChat.application.service;

import com.jongho.openChat.domain.model.OpenChat;
import com.jongho.openChat.domain.repository.OpenChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpenChatServiceImpl implements OpenChatService{
    private final OpenChatRepository openChatRepository;
    @Override
    public Optional<OpenChat> getLastOpenChatByOpenChatRoomId(Long openChatRoomId){
        return openChatRepository.selectLastOpenChatByChatRoomId(openChatRoomId);
    };
    @Override
    public int getUnReadOpenChatCountByOpenChatRoomIdAndLastExitTime(Long openChatRoomId, String lastExitTime, int limit){
        return openChatRepository.selectUnReadOpenChatCountByChatRoomIdAndLastExitTime(openChatRoomId, lastExitTime, limit);
    }


}
