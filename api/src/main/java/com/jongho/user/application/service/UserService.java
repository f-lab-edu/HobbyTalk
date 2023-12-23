package com.jongho.user.application.service;

import com.jongho.user.application.dto.request.UserSignUpDto;
import com.jongho.user.domain.model.User;

import java.util.Optional;

public interface UserService {
    public void signUp(UserSignUpDto userSignUpDto);
    public User getUser(String username);
    public Optional<User> getUserById(Long id);
}
