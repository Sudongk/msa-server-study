package com.example.ordercommand.domain.request;

import com.example.ordercommand.domain.entity.Order;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequest {

    private Long storeId;

    private String customerId;

    private Integer price;

    public Order toEntity() {
        return Order.builder()
                .storeId(storeId)
                .customerId(UUID.fromString(customerId))
                .price(price)
                .build();
    }

}
