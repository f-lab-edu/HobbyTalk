package com.jongho.user.application.service;

import com.jongho.user.application.dto.request.UserSignUpDto;
import com.jongho.user.domain.model.User;

public interface UserService {
    public int signUp(UserSignUpDto userSignUpDto);
    public User getUser(String username);
}
