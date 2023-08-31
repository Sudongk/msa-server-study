package com.example.ceo.domain.response;

import com.example.ceo.domain.entity.Owner;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OwnerResponse {

    private UUID id;
    private String name;
    private String number;

    public static OwnerResponse of(Owner owner) {
        return new OwnerResponse(owner.getId(), owner.getName(), owner.getNumber());
    }

}
