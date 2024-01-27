package com.jongho.openChatRoom.application.service;

import com.jongho.openChatRoom.domain.repository.OpenChatRoomRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenChatRoomRedisServiceImpl implements OpenChatRoomRedisService{
    private final OpenChatRoomRedisRepository openChatRoomRedisRepository;
    @Override
    public List<Long> getOpenChatRoomIdList(Long userId) {
        return openChatRoomRedisRepository.getOpenChatRoomIdList(userId);
    }
}
