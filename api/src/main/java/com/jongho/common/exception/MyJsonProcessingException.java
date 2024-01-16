package com.jongho.common.exception;

import org.springframework.http.HttpStatus;

public class MyJsonProcessingException extends CustomBusinessException{
    public MyJsonProcessingException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
