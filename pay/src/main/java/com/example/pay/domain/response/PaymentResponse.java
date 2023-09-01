package com.example.pay.domain.response;

import com.example.pay.domain.dto.Customer;
import com.example.pay.domain.entity.Payment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentResponse {

    private Long id;

    private Customer customer;

    private Integer price;

    private Long storeId;

    private List<MenuResponse> menus;

    private LocalDateTime createdAt;

    public PaymentResponse(Payment payment, List<MenuResponse> menus, Customer customer) {
        this.id = payment.getId();
        this.customer = customer;
        this.price = payment.getPrice();
        this.storeId = payment.getStoreId();
        this.menus = menus;
        this.createdAt = payment.getCreatedAt();
    }

}
