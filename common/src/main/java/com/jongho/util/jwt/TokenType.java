package com.jongho.util.jwt;

public enum TokenType {
    ACCESS_TOKEN(1),
    REFRESH_TOKEN(2);

    private final int value;

    TokenType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
