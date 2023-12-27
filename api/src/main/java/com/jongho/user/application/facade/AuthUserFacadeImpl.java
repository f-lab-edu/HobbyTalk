package com.jongho.user.application.facade;

import com.jongho.common.exception.UnAuthorizedException;
import com.jongho.common.util.bcrypt.BcryptUtil;
import com.jongho.common.auth.AuthUser;
import com.jongho.user.application.service.AuthUserService;
import com.jongho.user.application.service.UserService;
import com.jongho.user.domain.model.User;
import com.jongho.common.util.jwt.AccessPayload;
import com.jongho.common.util.jwt.JwtUtil;
import com.jongho.common.util.jwt.RefreshPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserFacadeImpl implements AuthUserFacade {
    private final UserService userService;
    private final AuthUserService authUserService;
    private final JwtUtil jwtUtil;
    @Override
    @Transactional
    public Map<String, String> signIn(String username, String password) {
        User user = userService.getUser(username);
        if(!BcryptUtil.checkPassword(password, user.getPassword())) {
            throw new UnAuthorizedException("비밀번호가 일치하지 않습니다.");
        }
        AccessPayload accessPayload = new AccessPayload(user.getId());
        RefreshPayload refreshPayload = new RefreshPayload(user.getId());

        String refreshToken = jwtUtil.createRefreshToken(refreshPayload);
        Optional<AuthUser> authUser = authUserService.getAuthUser(user.getId());
        if(authUser.isPresent()) {

            authUserService.updateRefreshToken(new AuthUser(user.getId(), refreshToken));
        } else {

            authUserService.createAuthUser(new AuthUser(user.getId(), refreshToken));
        }

        Map<String, String> result = new HashMap<>();
        result.put("accessToken", jwtUtil.createAccessToken(accessPayload));
        result.put("refreshToken", refreshToken);

        return result;
    }

    @Override
    @Transactional
    public Map<String, String> tokenRefresh(String refreshToken) {
        RefreshPayload refreshPayload = jwtUtil.validateRefreshToken(refreshToken);
        Optional<AuthUser> authUser = authUserService.getAuthUser(refreshPayload.getUserId());
        if(authUser.isPresent()) {
            if(refreshToken.equals(authUser.get().getRefreshToken())) {
                User user = userService.getUserById(refreshPayload.getUserId()).orElseThrow(()->{
                    throw new UnAuthorizedException("존재하지 않는 유저입니다.");
                });

                String newRefreshToken = jwtUtil.createRefreshToken(refreshPayload);
                authUserService.updateRefreshToken(new AuthUser(user.getId(), newRefreshToken));

                Map<String, String> result = new HashMap<>();
                result.put("accessToken", jwtUtil.createAccessToken(new AccessPayload(user.getId(), user.getUsername())));
                result.put("refreshToken", newRefreshToken);

                return result;
            }
        }
        throw new UnAuthorizedException("리프레시 토큰이 유효하지 않습니다.");
    }
}
