package com.example.store.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public TokenInfo parseToken(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();

        TokenInfo tokenInfo = TokenInfo.builder()
                .id(UUID.fromString(claims.get("id").toString()))
                .name(claims.get("name").toString())
                .number(claims.get("number").toString())
                .role(claims.get("role").toString())
                .build();

        if (!tokenInfo.getRole().equals("OWNER")) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        return tokenInfo;
    }

}
