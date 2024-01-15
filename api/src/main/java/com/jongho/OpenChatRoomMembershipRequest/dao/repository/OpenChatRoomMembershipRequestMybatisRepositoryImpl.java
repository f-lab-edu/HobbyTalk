package com.jongho.OpenChatRoomMembershipRequest.dao.repository;

import com.jongho.OpenChatRoomMembershipRequest.dao.mapper.OpenChatRoomMembershipRequestMapper;
import com.jongho.OpenChatRoomMembershipRequest.domain.model.OpenChatRoomMembershipRequest;
import com.jongho.OpenChatRoomMembershipRequest.domain.repository.OpenChatRoomMembershipRequestRepository;
import com.jongho.openChatRoom.domain.model.OpenChatRoom;
import com.jongho.openChatRoom.domain.repository.OpenChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OpenChatRoomMembershipRequestMybatisRepositoryImpl implements OpenChatRoomMembershipRequestRepository {
    private final OpenChatRoomMembershipRequestMapper openChatRoomMapper;
    @Override
    public int countByRequesterIdAndStatus(Long requesterId, int status) {
        return openChatRoomMapper.countByRequesterIdAndStatus(requesterId, status);
    }
    @Override
    public boolean existsByRequesterIdAndOpenChatRoomIdAndStatus(Long requesterId, Long openChatRoomId, int status) {
        return openChatRoomMapper.existsByRequesterIdAndOpenChatRoomIdAndStatus(requesterId, openChatRoomId, status);
    }
    @Override
    public void createOpenChatRoomMembershipRequest(OpenChatRoomMembershipRequest openChatRoomMembershipRequest) {
        openChatRoomMapper.createOpenChatRoomMembershipRequest(openChatRoomMembershipRequest);
    }
}
