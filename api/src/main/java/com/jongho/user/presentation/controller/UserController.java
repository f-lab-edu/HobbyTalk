package com.jongho.user.presentation.controller;

import com.jongho.annotaition.HttpRequestLogging;
import com.jongho.response.BaseResponseEntity;
import com.jongho.user.application.dto.request.UserSignUpDto;
import com.jongho.user.application.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@HttpRequestLogging
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public BaseResponseEntity<?> signUp(@Validated @RequestBody UserSignUpDto userSignUpDto){
        userService.signUp(userSignUpDto);

        return BaseResponseEntity.ok("user create");
    }
}