package com.jongho.common.interceptor;

import com.google.gson.Gson;
import com.jongho.user.domain.model.AuthUser;
import com.jongho.common.exception.UnAuthorizedException;
import com.jongho.common.response.BaseResponseEntity;
import com.jongho.common.util.jwt.AccessPayload;
import com.jongho.common.util.jwt.JwtUtil;
import com.jongho.user.application.service.AuthUserService;
import com.jongho.user.application.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;
    private final AuthUserService authUserService;
    private final UserService userService;
    private final ThreadLocal<Long> userIdThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("Authorization");
        if (accessToken != null) {
            return handleValidAccessToken(accessToken, request, response);
        } else {
            return handleInvalidAccessToken(response, "토큰이 존재하지 않습니다.");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        userIdThreadLocal.remove();
    }

    private boolean handleValidAccessToken(String accessToken, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            AccessPayload accessPayload = jwtUtil.validateAccessToken(accessToken);
            AuthUser authUser = authUserService.getAuthUser(accessPayload.getUserId()).orElseThrow(() -> new UnAuthorizedException("존재하지 않는 유저입니다."));

            // token-refresh PR merge 후 주석 해제
            // User user = userService.findOneById(authUser.getUserId()).orElseThrow(() -> new UnAuthorizedException("존재하지 않는 유저입니다."));
            userIdThreadLocal.set(authUser.getUserId());

            return true;
        } catch (UnAuthorizedException e) {

            return handleInvalidAccessToken(response, e.getMessage());
        }
    }

    private boolean handleInvalidAccessToken(HttpServletResponse response, String errorMessage) throws IOException {
        String result = new Gson().toJson(BaseResponseEntity.fail(HttpStatus.UNAUTHORIZED, errorMessage).getBody());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println(result);

        return false;
    }
}
