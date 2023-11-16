package com.jongho.user.application.service;

import com.jongho.common.exception.UserDuplicatedException;
import com.jongho.user.application.dto.request.UserSignUpDto;
import com.jongho.user.domain.model.User;
import com.jongho.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public int signUp(UserSignUpDto userSignUpDto) {
        userRepository.findOneByUsername(userSignUpDto.getUsername())
                .ifPresent((user)-> {
                    throw new UserDuplicatedException("이미 존재하는 아이디입니다.");
                });

        userRepository.findOneByPhoneNumber(userSignUpDto.getPhoneNumber())
                .ifPresent((user)-> {
                    throw new UserDuplicatedException("이미 가입된 전화번호입니다.");
                });

        return userRepository.createUser(userSignUpDto.toUser());
    }
}
