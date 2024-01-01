package com.jongho.openChatRoom.dao.repository;

import com.jongho.openChatRoom.dao.mapper.OpenChatRoomMapper;
import com.jongho.openChatRoom.domain.repository.OpenChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OpenChatRoomMybatisRepositoryImpl implements OpenChatRoomRepository {
    private final OpenChatRoomMapper openChatRoomMapper;
    @Override
    public int countByManagerId() {
        return openChatRoomMapper.countByManagerId();
    }
}
