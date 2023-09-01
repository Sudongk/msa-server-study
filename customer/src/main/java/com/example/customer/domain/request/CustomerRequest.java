package com.example.customer.domain.request;


import com.example.customer.domain.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CustomerRequest {

    private UUID id;

    private String name;

    private String number;

    public Customer toEntity() {
        return Customer.builder()
                .id(id)
                .name(name)
                .number(number)
                .build();
    }

}
