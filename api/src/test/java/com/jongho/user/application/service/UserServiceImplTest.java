package com.jongho.user.application.service;

import com.jongho.common.exception.UserDuplicatedException;
import com.jongho.user.application.dto.request.UserSignUpDto;
import com.jongho.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserServiceImpl 클래스")
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private UserSignUpDto user;
    @Nested
    @DisplayName("signUp 메소드는")
    class Describe_signUp {

        @BeforeEach
        void setUp() {
            user = new UserSignUpDto("jonghao", "a123b123", "whdgh9595", "01012341234", null);
        }
        @Test()
        @DisplayName("UserRepository.findByUsername()을 호출하고 결과가 있으면 UserDuplicatedException을 발생시킨다.")
        void 유저_아이디가_중복된_경우_UserDuplicatedException을_발생시킨다() {
            // given
            given(userRepository.findOneByUsername(user.getUsername())).willReturn(Optional.of(user.toUser()));

            // when // then
            UserDuplicatedException e = assertThrows(UserDuplicatedException.class, () -> userService.signUp(user));
            assertEquals("이미 존재하는 아이디입니다.", e.getMessage());
        }

        @Test()
        @DisplayName("UserRepository.findByPhoneNumber()을 호출하고 결과가 있으면 UserDuplicatedException을 발생시킨다.")
        void 유저_핸드폰번호가_중복된_경우_UserDuplicatedException을_발생시킨다() {
            // given
            given(userRepository.findOneByPhoneNumber(user.getPhoneNumber())).willReturn(Optional.of(user.toUser()));

            // when // then
            UserDuplicatedException e = assertThrows(UserDuplicatedException.class, () -> userService.signUp(user));
            assertEquals("이미 가입된 전화번호입니다.", e.getMessage());
        }
    }
}
