package com.jongho.common.advice;

import com.jongho.common.exception.CustomBusinessException;
import com.jongho.common.response.BaseResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(CustomBusinessException.class)
    public ResponseEntity<BaseResponseEntity<?>> CustomBusinessException(CustomBusinessException e) {
        System.out.println("CustomBusinessException");
        return getExceptionResponse(e.getMessage(), e.getHttpStatus());

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseEntity<?>> handleException(Exception e) {
        System.out.println("Exception");
        return getExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private ResponseEntity<BaseResponseEntity<?>> getExceptionResponse(String message, HttpStatus status) {
        return BaseResponseEntity.fail(status, message);
    }
}
