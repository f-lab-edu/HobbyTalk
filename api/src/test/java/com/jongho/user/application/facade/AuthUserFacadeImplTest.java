package com.jongho.user.application.facade;

import com.jongho.common.exception.UnAuthorizedException;
import com.jongho.common.util.BcryptUtil;
import com.jongho.domain.auth.AuthUser;
import com.jongho.user.application.dto.request.UserSignInDto;
import com.jongho.user.application.service.UserService;
import com.jongho.user.domain.model.User;
import com.jongho.util.jwt.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthUserFacadeImpl 클래스")
public class AuthUserFacadeImplTest {
    @Mock
    private UserService userService;
    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthUserFacadeImpl authUserFacadeImpl;


    @Nested
    @DisplayName("signIn 메소드는")
    class Describe_signIn {
        @Test
        @DisplayName("정상적인 데이터를 받으면 UserService.getUser를 호출하고 가져온 데이터의 패스워드와 요청받은 패스워드가 같으면 JwtUtil.createToken을 호출하고 토큰을 반환한다.")
        void 성공적인_요청의경우_아이디와_일치하는_데이터를_가져와_비밀번호를_비교하고_맞으면_토큰을_발급하고_반환한다(){
            // given
            UserSignInDto userSignInDto = new UserSignInDto("jonghao1@", "whdgh9595");
            User user = new User(1L, "whdgh9595", BcryptUtil.hashPassword("jonghao1@"), "whdgh9595", "01012341234", null);
            AuthUser authUser = new AuthUser(user.getId(), user.getUsername());

            when(jwtUtil.createToken(authUser)).thenReturn("token");
            when(userService.getUser(userSignInDto.getUsername())).thenReturn(user);

            // when
            String result = authUserFacadeImpl.signIn(userSignInDto.getUsername(), userSignInDto.getPassword());

            // then
             verify(userService, times(1)).getUser(userSignInDto.getUsername());
             verify(jwtUtil, times(1)).createToken(authUser);
             assertEquals("token", result);

        }

        @Test
        @DisplayName("UserService.getUser를 호출하고 가져온 데이터의 패스워드와 요청받은 패스워드가 다르면 UnAuthorizedException을 발생시킨다.")
        void 전달받은_패스워드와_데이터이_패스워드가_다르면_UnAuthorizedException을_발생시킨다(){
            UserSignInDto userSignInDto = new UserSignInDto("jonghao2@", "whdgh9595");
            User user = new User(1L, "whdgh9595", BcryptUtil.hashPassword("jonghao1@"), "whdgh9595", "01012341234", null);

            when(userService.getUser(userSignInDto.getUsername())).thenReturn(user);

            // when then
            Exception e = assertThrows(UnAuthorizedException.class,() -> authUserFacadeImpl.signIn(userSignInDto.getUsername(), userSignInDto.getPassword()));
            assertEquals("비밀번호가 일치하지 않습니다.", e.getMessage());
        }
    }


}
