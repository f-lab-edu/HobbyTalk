package com.jongho.common.util.websocket;

public enum BaseMessageTypeEnum {
    SEND("SEND"),
    PAGINATION("PAGINATION"),
    RECEIVE("RECEIVE"),
    JOIN("JOIN"),
    LEAVE("LEAVE"),
    ERROR("ERROR");
    private final String value;

    BaseMessageTypeEnum (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
