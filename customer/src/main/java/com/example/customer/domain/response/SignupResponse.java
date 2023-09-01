package com.example.customer.domain.response;

import com.example.customer.domain.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SignupResponse {

    private Customer customer;
    private String redirectUrl;

}
