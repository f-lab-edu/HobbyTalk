package com.jongho.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@RequiredArgsConstructor
public class BaseResponseEntity<T> {
    private int status;
    private String message;
    private T data;

    public BaseResponseEntity(T data, String message, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.status = status.value();
    }

    public BaseResponseEntity(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }

    public static<T> BaseResponseEntity<T> ok(T data, String message) {

        return new BaseResponseEntity<>(data, message, HttpStatus.OK);
    }

    public static BaseResponseEntity<?> ok(String message) {
        return new BaseResponseEntity<>(message, HttpStatus.OK);

    }

    public static BaseResponseEntity<?> create(String message) {
        return new BaseResponseEntity<>(message, HttpStatus.CREATED);
    }
}
