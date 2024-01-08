package com.jongho.openChatRoom.dao.repository;

import com.jongho.openChatRoom.application.dto.response.MyOpenChatRoomListDto;
import com.jongho.openChatRoom.dao.mapper.OpenChatRoomMapper;
import com.jongho.openChatRoom.domain.model.OpenChatRoom;
import com.jongho.openChatRoom.domain.repository.OpenChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    @Override
    public Optional<OpenChatRoom> selectOneOpenChatRoomByManagerIdAndTitle(Long managerId, String title) {
        return Optional.ofNullable(openChatRoomMapper.selectOneOpenChatRoomByManagerIdAndTitle(managerId, title));
    }
    @Override
    public List<MyOpenChatRoomListDto> selectMyOpenChatRoomListByUserId(Long userId){
        return openChatRoomMapper.selectMyOpenChatRoomListByUserId(userId);
    };
    @Override
    public List<MyOpenChatRoomListDto> selectMyOpenChatRoomListByUserIdAndOffset(Long userId, int offset){
        return openChatRoomMapper.selectMyOpenChatRoomListByUserIdAndOffset(userId, offset);
    };
}
