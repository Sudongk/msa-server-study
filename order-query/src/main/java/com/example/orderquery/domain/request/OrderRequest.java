package com.example.orderquery.domain.request;

import com.example.orderquery.domain.entity.Order;
import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrderRequest {

    private UUID id;

    private Long storeId;

    private UUID customerId;

    private Integer price;

    public Order toEntity() {
        return Order.builder()
                .id(id)
                .storeId(storeId)
                .customerId(customerId)
                .price(price)
                .build();
    }

}