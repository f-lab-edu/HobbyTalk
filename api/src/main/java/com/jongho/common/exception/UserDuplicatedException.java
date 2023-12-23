package com.jongho.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserDuplicatedException extends CustomBusinessException{
    public UserDuplicatedException(String message) {
            super(message, HttpStatus.CONFLICT);
        }
}
