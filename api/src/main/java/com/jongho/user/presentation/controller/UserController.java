package com.jongho.user.presentation.controller;

import com.jongho.response.BaseResponseEntity;
import com.jongho.user.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/ping")
    public BaseResponseEntity<?> ping(){
        System.out.println(userService.toString());
        return BaseResponseEntity.ok(userService.ping());
    }
}
