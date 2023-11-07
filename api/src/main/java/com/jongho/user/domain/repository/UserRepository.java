package com.jongho.user.domain.repository;

import com.jongho.user.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    public void create(User user);

    public Optional<User> findOneByEmail(String email);

    public Optional<User> findOneByPhoneNumber(String phoneNumber);
}
