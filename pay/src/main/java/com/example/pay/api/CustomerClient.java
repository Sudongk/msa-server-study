package com.example.pay.api;

import com.example.pay.domain.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerClient {

    @GetMapping("/api/v1/customer/me/{id}")
    ResponseEntity<Customer> getMeByToken(@PathVariable String id);

}
