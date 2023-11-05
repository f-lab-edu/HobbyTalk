package com.jongho.user.presentation.controller;

import com.jongho.annotaition.HttpRequestLogging;
import com.jongho.response.BaseResponseEntity;
import com.jongho.user.application.dto.request.UserSignUpDto;
import com.jongho.user.application.service.UserService;

import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

=======
import org.springframework.web.bind.annotation.*;

>>>>>>> 6889d35 ([ADD]http request 파라미터 및 실행 메소드 로깅 advice & annotation 작성)
@HttpRequestLogging
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

<<<<<<< HEAD
    @PostMapping("/sign-up")
    public ResponseEntity<BaseResponseEntity<?>> signUp(@Validated @RequestBody UserSignUpDto userSignUpDto){
        userService.signUp(userSignUpDto);

        return BaseResponseEntity.ok("user create");
=======
    @GetMapping("/ping")
    public BaseResponseEntity<?> ping(){
        return BaseResponseEntity.ok(userService.ping());
>>>>>>> 6889d35 ([ADD]http request 파라미터 및 실행 메소드 로깅 advice & annotation 작성)
    }
}