package com.example.ordercommand.service;

import com.example.ordercommand.api.OrderQueryClient;
import com.example.ordercommand.domain.entity.Order;
import com.example.ordercommand.domain.request.OrderRequest;
import com.example.ordercommand.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderQueryClient orderQueryClient;

    @Transactional
    public void save(OrderRequest request) {
        Order order = orderRepository.save(request.toEntity());

        orderQueryClient.save(order);

        orderRepository.delete(order);
    }

}
