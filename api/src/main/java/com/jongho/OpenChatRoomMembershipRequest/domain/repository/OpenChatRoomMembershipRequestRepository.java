package com.jongho.OpenChatRoomMembershipRequest.domain.repository;

import com.jongho.OpenChatRoomMembershipRequest.dao.mapper.OpenChatRoomMembershipRequestMapper;
import org.apache.ibatis.annotations.Param;

public interface OpenChatRoomMembershipRequestRepository {
    public int countByRequesterIdAndStatus(Long requesterId, int status);
    public boolean existsByRequesterIdAndOpenChatRoomIdAndStatus(Long requesterId, Long openChatRoomId, int status);
    public void createOpenChatRoomMembershipRequest(OpenChatRoomMembershipRequestMapper openChatRoomMembershipRequestMapper);
}
