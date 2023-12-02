package com.jongho.user.application.service;

import com.jongho.domain.auth.AuthUser;
import com.jongho.user.domain.repository.AuthUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthUserServiceImpl 클래스")
public class AuthUserServiceImplTest {
    @Mock
    private AuthUserRepository authUserRepository;
    @InjectMocks
    private AuthUserServiceImpl authUserServiceImpl;

    @Nested
    @DisplayName("createAuthUser 메소드는")
    class Describe_createAuthUser {
            @Test
            @DisplayName("AuthUserRepository의 createAuthUser 메소드를 호출한다")
            void AuthUserRepository의_createAuthUser_메소드를_한번_호출한다() {
                // given
                AuthUser authUser = new AuthUser(1L, "refreshToken", "userAgent");
                doNothing().when(authUserRepository).createAuthUser(authUser);

                // when
                authUserServiceImpl.createAuthUser(authUser);

                // then
                verify(authUserRepository, times(1)).createAuthUser(authUser);
            }
    }
}
