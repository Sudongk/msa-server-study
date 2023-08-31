package com.example.ceo.domain.request;

import com.example.ceo.domain.entity.Owner;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OwnerRequest {

    private UUID id;
    private String name;
    private String number;

    public Owner toEntity() {
        return Owner.builder()
                .id(id)
                .name(name)
                .number(number)
                .build();
    }

}
