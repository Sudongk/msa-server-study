package com.example.orderquery.service;

import com.example.orderquery.domain.entity.Order;
import com.example.orderquery.domain.request.OrderRequest;
import com.example.orderquery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void save(OrderRequest request) {
        Order order = orderRepository.save(request.toEntity());
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

}
