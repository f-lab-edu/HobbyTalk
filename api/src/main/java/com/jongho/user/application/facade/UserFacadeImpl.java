package com.jongho.user.application.facade;

import com.jongho.user.application.dto.request.UserSignUpDto;
import com.jongho.user.application.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade{
    private final UserService userService;
    @Override
    public void userSignUpAndAlarmCreate(UserSignUpDto userSignUpDto) {
        int userId = userService.signUp(userSignUpDto);

    }
}
