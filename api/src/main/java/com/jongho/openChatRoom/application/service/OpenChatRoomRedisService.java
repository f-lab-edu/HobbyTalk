package com.jongho.openChatRoom.application.service;

import java.util.List;

public interface OpenChatRoomRedisService {
    public List<Long> getOpenChatRoomIdList(Long userId);
}
