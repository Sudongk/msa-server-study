package com.example.auth.client.request;

import com.example.auth.domain.entity.User;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserRequest {

    private UUID id;
    private String name;
    private String number;

    public static UserRequest of(User user) {
        return UserRequest.builder()
                .id(user.getId())
                .name(user.getName())
                .number(user.getNumber())
                .build();
    }

}
