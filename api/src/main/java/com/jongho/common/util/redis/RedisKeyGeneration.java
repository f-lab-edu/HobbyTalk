package com.jongho.common.util.redis;

public class RedisKeyGeneration {
    public static String getChatRoomConnectionInfoKey(Long userId, Long openChatRoomId) {
        return "users:" + userId + ":chatRooms:" + openChatRoomId + ":connectionInfo";
    }
    public static String getJoinedChatRoomsKey(Long userId) {
        return "users:" + userId + ":chatRooms";
    }
    public static String getChatRoomKey(Long openChatRoomId) {
        return "chatRooms:" + openChatRoomId;
    }
    public static String getChatRoomMessageKey(Long openChatRoomId) {
        return "chatRooms:" + openChatRoomId + ":chats";
    }
}
