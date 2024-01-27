package com.jongho.openChatRoom.application.dto.response;

import com.jongho.openChat.domain.model.OpenChat;
import com.jongho.openChatRoom.domain.model.redis.RedisOpenChatRoomConnectionInfo;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
@AllArgsConstructor
public class OpenChatRoomDto {
    private final Long id;
    private final String title;
    private final String notice;
    private final Long managerId;
    private final Long categoryId;
    private final int maximumCapacity;
    private final int currentAttendance;
    private final String createdTime;
    private OpenChat lastChat;
    private RedisOpenChatRoomConnectionInfo redisOpenChatRoomConnectionInfo;

    public void setOpenChat(OpenChat lastChat) {
        this.lastChat = lastChat;
    }

    public void setRedisOpenChatRoomConnectionInfo(RedisOpenChatRoomConnectionInfo redisOpenChatRoomConnectionInfo) {
        this.redisOpenChatRoomConnectionInfo = redisOpenChatRoomConnectionInfo;
    }
}
