package com.jongho.user.dao.repository;

import com.jongho.user.dao.mapper.UserMapper;
import com.jongho.user.domain.model.User;
import com.jongho.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserMyBatisRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    @Override
    public void create(User user) {
        userMapper.create(user);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return Optional.ofNullable(userMapper.findOneByEmail(email));
    }

    @Override
    public Optional<User> findOneByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(userMapper.findOneByPhoneNumber(phoneNumber));
    }

}
