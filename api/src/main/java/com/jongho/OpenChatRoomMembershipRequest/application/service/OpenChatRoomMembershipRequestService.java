package com.jongho.OpenChatRoomMembershipRequest.application.service;

import com.jongho.OpenChatRoomMembershipRequest.domain.model.OpenChatRoomMembershipRequest;

public interface OpenChatRoomMembershipRequestService {
    public int countByRequesterIdAndStatus(Long requesterId, int status);
    public boolean existsByRequesterIdAndOpenChatRoomIdAndStatus(Long requesterId, Long openChatRoomId, int status);
    public void createOpenChatRoomMembershipRequest(OpenChatRoomMembershipRequest openChatRoomMembershipRequest);
}
