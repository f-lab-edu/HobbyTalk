package com.jongho.user.dao.repository;

import com.jongho.user.dao.mapper.UserMapper;
import com.jongho.user.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserMyBatisRepositoryImpl implements UserRepository {
    UserMapper userMapper;
}
