package com.example.store.domain.response;

import com.example.store.domain.entity.Store;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreResponse {

    private Long id;
    private String location;
    private String name;
    private String img;

    public static StoreResponse of(Store store) {
        return new StoreResponse(store.getId(), store.getLocation(), store.getName(), store.getImg());
    }

}
