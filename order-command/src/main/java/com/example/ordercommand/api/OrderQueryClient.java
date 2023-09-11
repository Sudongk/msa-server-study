package com.example.ordercommand.api;

import com.example.ordercommand.domain.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ORDER-QUERY-SERVICE")
public interface OrderQueryClient {

    @PostMapping("/api/v1/order")
    ResponseEntity<Void> save(@RequestBody Order order);

}
