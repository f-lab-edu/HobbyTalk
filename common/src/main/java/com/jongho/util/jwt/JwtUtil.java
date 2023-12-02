package com.jongho.util.jwt;

import com.jongho.domain.auth.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.websocket.AuthenticationException;
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
        claims.put("username", accessPayload.getUsername());
        claims.put("isAccessToken", accessPayload.getIsAccessToken());

        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 60); // 1시간

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
        claims.put("isAccessToken", refreshPayload.getIsAccessToken());

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

            return new AccessPayload(claims.get("userId", Long.class), claims.get("username", String.class));
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, "토큰이 만료되었습니다.");
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

            return new RefreshPayload(claims.get("userId", Long.class));
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, "리프레시 토큰이 만료되었습니다.");
        } catch (JwtException e) {
            throw new JwtException("리프레시 토큰이 유효하지 않습니다.");
        }
    }
}
