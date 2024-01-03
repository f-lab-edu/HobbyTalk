package com.jongho.openChatRoom.dao.repository;

import com.jongho.openChatRoom.dao.mapper.OpenChatRoomMapper;
import com.jongho.openChatRoom.domain.model.OpenChatRoom;
import com.jongho.openChatRoom.domain.repository.OpenChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OpenChatRoomMybatisRepositoryImpl implements OpenChatRoomRepository {
    private final OpenChatRoomMapper openChatRoomMapper;
    @Override
    public int countByManagerId(Long managerId) {
        return openChatRoomMapper.countByManagerId(managerId);
    }
    @Override
    public void createOpenChatRoom(OpenChatRoom openChatRoom) {
        openChatRoomMapper.createOpenChatRoom(openChatRoom);
    }
    @Override
    public void updateIncrementCurrentCapacity(Long openChatRoomId, int currentAttendance) {
        openChatRoomMapper.updateIncrementCurrentCapacity(openChatRoomId, currentAttendance);
    }
}
