package com.example.store.domain.request;

import com.example.store.domain.entity.Owner;
import com.example.store.domain.entity.Store;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class StoreRequest {

    private String location;
    private String name;
    private String img;

    public Store toEntity(UUID ownerId) {
        return Store.builder()
                .location(location)
                .name(name)
                .img(img)
                .owner(
                        Owner.builder()
                                .id(ownerId)
                                .build()
                )
                .build();
    }

}
