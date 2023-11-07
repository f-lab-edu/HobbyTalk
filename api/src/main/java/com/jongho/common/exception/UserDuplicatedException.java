package com.jongho.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserDuplicatedException extends RuntimeException{
        private final HttpStatus httpStatus = HttpStatus.CONFLICT;
        public UserDuplicatedException(String message) {
            super(message);
        }
}
