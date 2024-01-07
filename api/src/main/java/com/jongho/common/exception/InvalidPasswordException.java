package com.jongho.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends CustomBusinessException{
    public InvalidPasswordException(String message) {
        super(message, HttpStatus.FORBIDDEN);

    }
}
