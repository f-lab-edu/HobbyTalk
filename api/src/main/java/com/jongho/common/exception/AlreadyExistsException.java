package com.jongho.common.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends CustomBusinessException{
    public AlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
