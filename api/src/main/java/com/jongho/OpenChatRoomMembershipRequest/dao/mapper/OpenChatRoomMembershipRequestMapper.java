package com.jongho.OpenChatRoomMembershipRequest.dao.mapper;

import com.jongho.OpenChatRoomMembershipRequest.domain.model.OpenChatRoomMembershipRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OpenChatRoomMembershipRequestMapper {

    public int countByRequesterIdAndStatus(@Param("requester_id") Long requesterId, @Param("status") int status);
    public boolean existsByRequesterIdAndOpenChatRoomIdAndStatus(@Param("requester_id") Long requesterId, @Param("open_chat_room_id") Long openChatRoomId, @Param("status") int status);
    public void createOpenChatRoomMembershipRequest(OpenChatRoomMembershipRequest openChatRoomMembershipRequest);
}
