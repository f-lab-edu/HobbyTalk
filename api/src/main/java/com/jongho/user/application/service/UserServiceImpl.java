package com.jongho.user.application.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String ping() {
        return "pong";
    }
}
