package com.jongho.openChatRoom.application.service;

import com.jongho.openChatRoom.domain.model.OpenChatRoom;
import com.jongho.openChatRoom.domain.repository.OpenChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenChatRoomServiceImpl implements OpenChatRoomService{
    private final OpenChatRoomRepository openChatRoomRepository;
    @Override
    public void createOpenChatRoom(OpenChatRoom openChatRoom) {
        openChatRoomRepository.createOpenChatRoom(openChatRoom);
    }
    @Override
    public int getCountByManagerId(Long managerId) {
        return openChatRoomRepository.countByManagerId(managerId);
    }
    @Override
    public void updateIncrementCurrentCapacity(Long openChatRoomId) {
        openChatRoomRepository.updateIncrementCurrentCapacity(openChatRoomId);
    }
}
