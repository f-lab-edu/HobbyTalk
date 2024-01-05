package com.jongho.openChatRoomUser.dao.repository;

import com.jongho.openChatRoomUser.dao.mapper.OpenChatRoomUserMapper;
import com.jongho.openChatRoomUser.domain.model.OpenChatRoomUser;
import com.jongho.openChatRoomUser.domain.repository.OpenChatRoomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OpenChatRoomUserMybatisRepositoryImpl implements OpenChatRoomUserRepository {
    private final OpenChatRoomUserMapper openChatRoomUserMapper;
    @Override
    public void createOpenChatRoomUser(OpenChatRoomUser openChatRoomUser) {
        openChatRoomUserMapper.createOpenChatRoomUser(openChatRoomUser);
    }
}
