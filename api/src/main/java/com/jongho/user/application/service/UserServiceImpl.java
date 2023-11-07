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
    public void signUp(UserSignUpDto userSignUpDto) {
        User user = userSignUpDto.toUser();
        if(userRepository.findOneByEmail(user.getEmail()).isPresent() ){
            throw new UserDuplicatedException("이미 존재하는 이메일입니다.");
        }

        if(userRepository.findOneByPhoneNumber(user.getPhoneNumber()).isPresent()){
            throw new UserDuplicatedException("이미 가입된 전화번호입니다.");
        }

        userRepository.create(user);
    }
}
