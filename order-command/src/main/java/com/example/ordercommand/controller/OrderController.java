package com.example.ordercommand.controller;

import com.example.ordercommand.domain.request.OrderRequest;
import com.example.ordercommand.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void save(@RequestBody OrderRequest request) {
        orderService.save(request);
    }

}
