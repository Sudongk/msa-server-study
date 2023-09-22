package com.example.pay.domain.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderKafkaData {

    private Long storeId;
    private String customerId;
    private Integer price;

}
