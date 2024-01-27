package com.jongho.openChatRoom.domain.repository;

import java.util.List;

public interface OpenChatRoomRedisRepository {
    public List<Long> getOpenChatRoomIdList(Long userId);
}
