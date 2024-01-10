package com.jongho.openChatRoom.application.service;

import com.jongho.common.exception.OpenChatRoomNotFoundException;
import com.jongho.common.exception.UnAuthorizedException;
import com.jongho.openChatRoom.domain.model.OpenChatRoom;
import com.jongho.openChatRoom.domain.repository.OpenChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpenChatRoomServiceImpl implements OpenChatRoomService{
    private final OpenChatRoomRepository openChatRoomRepository;
    @Override
    public void createOpenChatRoom(OpenChatRoom openChatRoom) {
        openChatRoomRepository.createOpenChatRoom(openChatRoom);
    }
    @Override
    public int getOpenChatRoomCountByManagerId(Long managerId) {
        return openChatRoomRepository.countByManagerId(managerId);
    }
    @Override
    public void incrementOpenChatRoomCurrentAttendance(Long openChatRoomId, int currentAttendance) {
        openChatRoomRepository.updateIncrementCurrentCapacity(openChatRoomId, currentAttendance);
    }
    @Override
    public Optional<OpenChatRoom> getOpenChatRoomByManagerIdAndTitle(Long managerId, String title) {
        return openChatRoomRepository.selectOneOpenChatRoomByManagerIdAndTitle(managerId, title);
    }
    @Override
    public Optional<OpenChatRoom> getOpenChatRoomByIdForUpdate(Long openChatRoomId) {
        return openChatRoomRepository.selectOneOpenChatRoomByIdForUpdate(openChatRoomId);
    }
    @Override
    public Optional<OpenChatRoom> getOpenChatRoomById(Long openChatRoomId) {
        return openChatRoomRepository.selectOneOpenChatRoomById(openChatRoomId);
    }
    @Override
    public void updateOpenChatRoomNotice(Long userId, Long openChatRoomId, String notice) {
        OpenChatRoom openChatRoom= this.getOpenChatRoomById(openChatRoomId).orElseThrow(() -> new OpenChatRoomNotFoundException("존재하지 않는 채팅방입니다."));
        if(openChatRoom.getId().equals(userId)){
            throw new UnAuthorizedException("채팅방 공지 수정 권한이 없습니다.");
        }
        openChatRoomRepository.updateOpenChatRoomNotice(openChatRoomId, notice);
    }
}
