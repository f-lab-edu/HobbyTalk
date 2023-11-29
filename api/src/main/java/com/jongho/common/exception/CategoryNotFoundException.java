package com.jongho.common.exception;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends CustomBusinessException{
    public CategoryNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
