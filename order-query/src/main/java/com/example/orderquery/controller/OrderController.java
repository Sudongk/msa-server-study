package com.example.orderquery.controller;

import com.example.orderquery.domain.request.OrderRequest;
import com.example.orderquery.service.OrderService;
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
