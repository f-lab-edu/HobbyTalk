package com.jongho.common.exception;

import org.springframework.http.HttpStatus;

public class MaxChatRoomsExceededException extends CustomBusinessException{
    public MaxChatRoomsExceededException(String message) {
        super(message, HttpStatus.BAD_REQUEST);

    }
}
