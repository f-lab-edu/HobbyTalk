package com.jongho.common.util.jwt;

import com.jongho.common.exception.UnAuthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final String secretKey;
    private final String algorithm;

    private Key secretKeyHash(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public JwtUtil(@Qualifier("jwtSecretKey") String secretKey, @Qualifier("jwtAlgorithm") String algorithm) {
        this.secretKey = secretKey;
        this.algorithm = algorithm;
    }

    public String createAccessToken(AccessPayload accessPayload) {
        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", algorithm);

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userId", accessPayload.getUserId());
        claims.put("tokenType", accessPayload.getTokenType().getValue());

        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 10); // 10분

        return Jwts.builder()
                .setHeader(headerMap)
                .setClaims(claims)
                .setExpiration(expireTime)
                .signWith(secretKeyHash(), io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(RefreshPayload refreshPayload) {
        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", algorithm);

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userId", refreshPayload.getUserId());
        claims.put("tokenType", refreshPayload.getTokenType().getValue());

        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 60 * 24 * 14); // 2주

        return Jwts.builder()
                .setHeader(headerMap)
                .setClaims(claims)
                .setExpiration(expireTime)
                .signWith(secretKeyHash(), io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();
    }

    public AccessPayload validateAccessToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKeyHash())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            if (claims.get("tokenType", Integer.class) != TokenType.ACCESS_TOKEN.getValue()) {
                throw new UnAuthorizedException("올바른 토큰타입이 아닙니다.");
            }

            return new AccessPayload(
                    claims.get("userId", Long.class));
        } catch (ExpiredJwtException e) {
            throw new UnAuthorizedException("토큰이 만료되었습니다.");
        } catch (JwtException e) {
            throw new JwtException("토큰이 유효하지 않습니다.");
        }
    }

    public RefreshPayload validateRefreshToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKeyHash())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            if (claims.get("tokenType", Integer.class) != TokenType.REFRESH_TOKEN.getValue()) {
                throw new UnAuthorizedException(("올바른 토큰타입이 아닙니다."));
            }

            return new RefreshPayload(claims.get("userId", Long.class));
        } catch (ExpiredJwtException e) {
            throw new UnAuthorizedException("리프레시 토큰이 만료되었습니다.");
        } catch (JwtException e) {
            throw new UnAuthorizedException(e.getMessage());
        }
    }
}
