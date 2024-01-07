package com.jongho.common.exception;

import org.springframework.http.HttpStatus;

public class OpenChatRoonNotFoundException extends CustomBusinessException{
    public OpenChatRoonNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
