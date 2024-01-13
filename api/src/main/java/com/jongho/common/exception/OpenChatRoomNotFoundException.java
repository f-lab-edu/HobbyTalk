package com.jongho.common.exception;

import org.springframework.http.HttpStatus;

public class OpenChatRoomNotFoundException extends CustomBusinessException{
    public OpenChatRoomNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
