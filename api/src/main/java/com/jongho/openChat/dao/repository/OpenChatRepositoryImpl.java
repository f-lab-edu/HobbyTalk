package com.jongho.openChat.dao.repository;

import com.jongho.openChat.dao.mapper.OpenChatMapper;
import com.jongho.openChat.domain.model.OpenChat;
import com.jongho.openChat.domain.repository.OpenChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OpenChatRepositoryImpl implements OpenChatRepository {
    private final OpenChatMapper openChatMapper;

    @Override
    public Optional<OpenChat> selectLastOpenChatByChatRoomId(Long openChatRoomId) {
        return Optional.ofNullable(openChatMapper.selectLastOpenChatByChatRoomId(openChatRoomId));
    }
}
