package com.example.pay.domain.request;

import com.example.pay.config.TokenInfo;
import com.example.pay.domain.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PaymentRequest {

    private final Integer price;
    private final Long storeId;
    private final String menuIds;

    public Payment toEntity(TokenInfo tokenInfo) {
        return Payment.builder()
                .createdAt(LocalDateTime.now())
                .price(price)
                .storeId(storeId)
                .menuIds(menuIds)
                .customerId(tokenInfo.getId())
                .build();
    }

}
