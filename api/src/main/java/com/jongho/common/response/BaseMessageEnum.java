package com.jongho.common.response;

public enum BaseMessageEnum {
    SUCCESS("SUCCESS"),
    CREATED("CREATED");
    private final String value;

    BaseMessageEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
