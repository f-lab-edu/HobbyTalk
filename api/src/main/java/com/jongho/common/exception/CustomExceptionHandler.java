package com.jongho.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        // 유효성 검사 실패로 인한 예외 처리 로직 작성
        return ResponseEntity.badRequest().body("유효성 검사 실패: " + ex.getBindingResult().getAllErrors());
    }
}