package com.jongho.user.application.facade;

import com.jongho.common.exception.UnAuthorizedException;
import com.jongho.common.util.BcryptUtil;
import com.jongho.domain.auth.AuthUser;
import com.jongho.user.application.service.UserService;
import com.jongho.user.domain.model.User;
import com.jongho.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserFacadeImpl implements AuthUserFacade {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    @Override
    public String signIn(String username, String password) {
        User user = userService.getUser(username);
        if(!BcryptUtil.checkPassword(password, user.getPassword())) {
            throw new UnAuthorizedException("비밀번호가 일치하지 않습니다.");
        }
        AuthUser authUser = new AuthUser(user.getId(), user.getUsername());

        return jwtUtil.createToken(authUser);
    }
}
