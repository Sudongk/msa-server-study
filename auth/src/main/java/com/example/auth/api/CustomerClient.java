package com.example.auth.api;

import com.example.auth.client.request.UserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerClient {

    @PostMapping("/api/v1/customer")
    ResponseEntity<Void> saveCustomer(UserRequest userRequest);

}
