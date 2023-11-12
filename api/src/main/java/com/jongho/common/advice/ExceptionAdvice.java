package com.jongho.common.advice;

import com.jongho.common.exception.UserDuplicatedException;
import com.jongho.response.BaseResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(UserDuplicatedException.class)
    public ResponseEntity<BaseResponseEntity<?>> handleUserDuplicatedException(UserDuplicatedException e) {
        return getExceptionResponse(e.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseEntity<?>> handleException(Exception e) {
        return getExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private ResponseEntity<BaseResponseEntity<?>> getExceptionResponse(String message, HttpStatus status) {
        return BaseResponseEntity.fail(status, message);
    }
}
