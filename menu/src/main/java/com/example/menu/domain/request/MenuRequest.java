package com.example.menu.domain.request;

import com.example.menu.domain.entity.Menu;
import com.example.menu.domain.entity.Store;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MenuRequest {

    private String name;
    private Integer price;
    private Long storeId;

    public Menu toEntity() {
        return Menu.builder()
                .name(name)
                .price(price)
                .store(
                        Store.builder()
                        .id(storeId)
                        .build()
                )
                .build();
    }
}
