package com.jongho.common.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomBusinessException{
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
