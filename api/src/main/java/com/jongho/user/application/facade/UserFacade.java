package com.jongho.user.application.facade;

import com.jongho.user.application.dto.request.UserSignUpDto;

public interface UserFacade {
    public void signUpUserAndCreateNotificationSetting(UserSignUpDto userSignUpDto, String userAgent);
}
