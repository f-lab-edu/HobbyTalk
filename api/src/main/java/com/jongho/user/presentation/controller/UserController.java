package com.jongho.user.presentation.controller;

import com.jongho.annotaition.HttpRequestLogging;
import com.jongho.common.exception.UserDuplicatedException;
import com.jongho.response.BaseResponseEntity;
import com.jongho.user.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@HttpRequestLogging
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/ping")
    public ResponseEntity<?> ping(){
        throw new UserDuplicatedException("이미 존재하는 유저입니다.");
    }
}