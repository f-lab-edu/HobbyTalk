package com.jongho.OpenChatRoomMembershipRequest.domain.repository;

import com.jongho.OpenChatRoomMembershipRequest.domain.model.OpenChatRoomMembershipRequest;

public interface OpenChatRoomMembershipRequestRepository {
    public int countByRequesterIdAndStatus(Long requesterId, int status);
    public boolean existsByRequesterIdAndOpenChatRoomIdAndStatus(Long requesterId, Long openChatRoomId, int status);
    public void createOpenChatRoomMembershipRequest(OpenChatRoomMembershipRequest openChatRoomMembershipRequest);
}
