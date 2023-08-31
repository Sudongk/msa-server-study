package com.example.auth.config;

import com.example.auth.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        Map<String, Object> claims = Map.of(
                "id", user.getId().toString(),
                "name", user.getName(),
                "number", user.getNumber(),
                "role", user.getRole().toString()
        );

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getId().toString())
                // 30일
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30))
                // HS256
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public TokenInfo parseToken(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return TokenInfo.builder()
                .id(UUID.fromString(claims.get("id").toString()))
                .name(claims.get("name").toString())
                .number(claims.get("number").toString())
                .role(claims.get("role").toString())
                .build();
    }

}