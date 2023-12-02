package com.jongho.user.application.facade;

import com.jongho.common.exception.UnAuthorizedException;
import com.jongho.common.util.BcryptUtil;
import com.jongho.domain.auth.AuthUser;
import com.jongho.user.application.dto.request.UserSignInDto;
import com.jongho.user.application.service.AuthUserService;
import com.jongho.user.application.service.UserService;
import com.jongho.user.domain.model.User;
import com.jongho.util.jwt.AccessPayload;
import com.jongho.util.jwt.JwtUtil;
import com.jongho.util.jwt.RefreshPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthUserFacadeImpl 클래스")
public class AuthUserFacadeImplTest {
    @Mock
    private UserService userService;
    @Mock
    private AuthUserService authUserService;
    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthUserFacadeImpl authUserFacadeImpl;


    @Nested
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("signIn 메소드는")
    class Describe_signIn {
        private UserSignInDto userSignInDto;
        private User user;
        private AuthUser authUser;
        private AccessPayload accessPayload;
        private RefreshPayload refreshPayload;
        private OngoingStubbing<String> mockJwtUtilCreateAccessToken;
        private OngoingStubbing<String> mockJwtUtilCreateRefreshToken;
        private OngoingStubbing<Optional<AuthUser>> mockAuthUserServiceGetAuthUser;
        private OngoingStubbing<User> mockUserServiceGetUser;

        private final String userAgent = "userAgent";
        private final String accessToekn = "token";
        private final String refreshToken = "refreshToken";


        @BeforeEach
        void setUp() {
            userSignInDto = new UserSignInDto("jonghao1@", "whdgh9595");
            user = new User(1L, "whdgh9595", BcryptUtil.hashPassword("jonghao1@"), "whdgh9595", "01012341234", null);
            authUser = new AuthUser(1L, "userAgent", refreshToken);
            accessPayload = new AccessPayload(user.getId(), user.getUsername());
            refreshPayload = new RefreshPayload(user.getId());
            mockJwtUtilCreateAccessToken = when(jwtUtil.createAccessToken(accessPayload)).thenReturn(accessToekn);
            mockJwtUtilCreateRefreshToken = when(jwtUtil.createRefreshToken(refreshPayload)).thenReturn(refreshToken);
            mockUserServiceGetUser = when(userService.getUser(userSignInDto.getUsername())).thenReturn(user);
            mockAuthUserServiceGetAuthUser = when(authUserService.getAuthUser(user.getId(), userAgent)).thenReturn(Optional.empty());
            doNothing().when(authUserService).createAuthUser(authUser);
            doNothing().when(authUserService).updateRefreshToken(authUser);
        }
        @Test
        @DisplayName("정상적인 데이터를 받으면 UserService.getUser를 호출하고 가져온 데이터의 패스워드와 요청받은 패스워드가 같으면 JwtUtil.createToken을 호출하고 토큰을 반환한다.")
        void 성공적인_요청의경우_아이디와_일치하는_데이터를_가져와_비밀번호를_비교하고_맞으면_토큰을_발급하고_반환한다(){
            // when
            Map<String, String> result = authUserFacadeImpl.signIn(userSignInDto.getUsername(), userSignInDto.getPassword(), userAgent);

            // then
             verify(userService, times(1)).getUser(userSignInDto.getUsername());
             verify(authUserService, times(1)).getAuthUser(user.getId(), userAgent);
             verify(authUserService, times(1)).createAuthUser(authUser);
             verify(jwtUtil, times(1)).createAccessToken(accessPayload);
             verify(jwtUtil, times(1)).createRefreshToken(refreshPayload);
             assertEquals("token", result.get("accessToken"));
             assertEquals("refreshToken", result.get("refreshToken"));

        }

        @Test
        @DisplayName("UserService.getUser를 호출하고 가져온 데이터의 패스워드와 요청받은 패스워드가 다르면 UnAuthorizedException을 발생시킨다.")
        void 전달받은_패스워드와_데이터이_패스워드가_다르면_UnAuthorizedException을_발생시킨다(){
            // given
            userSignInDto = new UserSignInDto("jonghao1!", "whdgh9595");

            // when then
            Exception e = assertThrows(UnAuthorizedException.class,() -> authUserFacadeImpl.signIn(userSignInDto.getUsername(), userSignInDto.getPassword(), userAgent));
            assertEquals("비밀번호가 일치하지 않습니다.", e.getMessage());
        }

        @Test
        @DisplayName("한번 이상 로그인을 해서 AuthUser가 존재하면 updateRefreshToken을 호출한다.")
        void 한번_이상_로그인을_해서_AuthUser가_존재하면_AuthUserService_updateRefreshToken을_호출한다(){
            // given
            when(authUserService.getAuthUser(user.getId(), userAgent)).thenReturn(Optional.of(authUser));

            // when
            authUserFacadeImpl.signIn(userSignInDto.getUsername(), userSignInDto.getPassword(), userAgent);

            // then
            verify(authUserService, times(1)).updateRefreshToken(authUser);
        }

        @Test
        @DisplayName("한번도 로그인을 안해서 AuthUser가 존재하지 않으면 createAuthUser를 호출한다.")
        void 한번_이상_로그인을_해서_AuthUser가_존재하면_AuthUserService_create을_호출한다(){
            // when
            authUserFacadeImpl.signIn(userSignInDto.getUsername(), userSignInDto.getPassword(), userAgent);

            // then
            verify(authUserService, times(1)).createAuthUser(authUser);
        }

    }


}
