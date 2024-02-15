package com.jongho.openChat.common.enums;

public enum OpenChatTypeEnum {
    TEXT(1),
    IMAGE(2),
    VIDEO(3),
    NOTIFICATION(4);

    private final int type;

    OpenChatTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
