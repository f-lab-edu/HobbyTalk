package com.jongho.OpenChatRoomMembershipRequest.application.service;

import com.jongho.OpenChatRoomMembershipRequest.dao.mapper.OpenChatRoomMembershipRequestMapper;

public interface OpenChatRoomMembershipRequestService {
    public int countByRequesterIdAndStatus(Long requesterId, int status);
    public boolean existsByRequesterIdAndOpenChatRoomIdAndStatus(Long requesterId, Long openChatRoomId, int status);
    public void createOpenChatRoomMembershipRequest(OpenChatRoomMembershipRequestMapper openChatRoomMembershipRequestMapper);
}
