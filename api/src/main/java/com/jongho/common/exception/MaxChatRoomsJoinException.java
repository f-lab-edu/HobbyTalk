package com.jongho.common.exception;

import org.springframework.http.HttpStatus;

public class MaxChatRoomsJoinException extends CustomBusinessException{
    public MaxChatRoomsJoinException(String message) {
        super(message, HttpStatus.BAD_REQUEST);

    }
}
