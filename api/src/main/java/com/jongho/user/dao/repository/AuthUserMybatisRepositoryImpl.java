package com.jongho.user.dao.repository;

import com.jongho.domain.auth.AuthUser;
import com.jongho.user.dao.mapper.AuthUserMapper;
import com.jongho.user.domain.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthUserMybatisRepositoryImpl implements AuthUserRepository {
    private final AuthUserMapper authUserMapper;

    public int createAuthUser(AuthUser authUser) {
        return authUserMapper.createAuthUser(authUser);
    }

    public void updateRefreshToken(AuthUser authUser) {
        authUserMapper.updateRefreshToken(authUser);
    }

    public Optional<AuthUser> selectOneAuthUser(Long userId, String userAgent) {

        return Optional.ofNullable(authUserMapper.selectOneAuthUser(userId, userAgent));
    }
}
