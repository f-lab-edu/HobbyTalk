package com.jongho.user.application.service;

import com.jongho.user.application.dto.request.UserSignUpDto;

public interface UserService {
    public void signUp(UserSignUpDto userSignUpDto);
}
