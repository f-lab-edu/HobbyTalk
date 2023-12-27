package com.jongho.user.presentation.controller;

import com.jongho.common.annotaition.HttpRequestLogging;
import com.jongho.common.response.BaseResponseEntity;
import com.jongho.user.application.dto.request.TokenRefreshDto;
import com.jongho.user.application.dto.request.UserSignInDto;
import com.jongho.user.application.dto.request.UserSignUpDto;
import com.jongho.user.application.dto.response.TokenReponseDto;
import com.jongho.user.application.facade.AuthUserFacade;
import com.jongho.user.application.facade.UserFacade;
import com.jongho.user.application.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@HttpRequestLogging
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserFacade userFacade;
    private final AuthUserFacade authUserFacade;

    @PostMapping("/sign-up")
    public ResponseEntity<BaseResponseEntity<?>> signUp(@Validated @RequestBody UserSignUpDto userSignUpDto) {
        userFacade.signUpUserAndCreateNotificationSetting(userSignUpDto);

        return BaseResponseEntity.create("user create");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<BaseResponseEntity<TokenReponseDto>> signIn(@Validated @RequestBody UserSignInDto userSignUpDto) {
        TokenReponseDto result = authUserFacade.signIn(userSignUpDto.getUsername(), userSignUpDto.getPassword());

        return BaseResponseEntity.ok(result, "success");
    }

    @PostMapping("/token-refresh")
    public ResponseEntity<BaseResponseEntity<TokenReponseDto>> tokenRefresh(@RequestBody TokenRefreshDto tokenRefreshDto) {
        TokenReponseDto result = authUserFacade.tokenRefresh(tokenRefreshDto.getRefreshToken());

        return BaseResponseEntity.ok(result, "success");
    }
}