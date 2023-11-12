package com.jongho.user.dao.mapper;

import com.jongho.user.domain.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    public int create(User user);

    public User findOneByUsername(String username);

    public User findOneByPhoneNumber(String phoneNumber);
}
