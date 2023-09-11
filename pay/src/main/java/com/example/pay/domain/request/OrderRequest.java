package com.example.pay.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderRequest {

    private Long storeId;

    private String customerId;

    private Integer price;

}
