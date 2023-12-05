package com.jongho.user.application.facade;

import com.jongho.common.exception.UnAuthorizedException;
import com.jongho.common.util.BcryptUtil;
import com.jongho.domain.auth.AuthUser;
import com.jongho.user.application.service.AuthUserService;
import com.jongho.user.application.service.UserService;
import com.jongho.user.domain.model.User;
import com.jongho.util.jwt.AccessPayload;
import com.jongho.util.jwt.JwtUtil;
import com.jongho.util.jwt.RefreshPayload;
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
        AccessPayload accessPayload = new AccessPayload(user.getId(), user.getUsername());
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
}