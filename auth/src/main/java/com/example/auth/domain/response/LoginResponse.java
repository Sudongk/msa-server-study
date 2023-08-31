package com.example.auth.domain.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LoginResponse {

    private String token;
    private String role;

    public static LoginResponse of(String token, String role) {
        return new LoginResponse(token, role);
    }

}
