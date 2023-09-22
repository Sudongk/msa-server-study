package com.example.ordercommand.service;

import com.example.ordercommand.api.OrderQueryClient;
import com.example.ordercommand.domain.entity.Order;
import com.example.ordercommand.domain.request.OrderRequest;
import com.example.ordercommand.kafka.OrderQueryProducer;
import com.example.ordercommand.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderQueryClient orderQueryClient;
    private final OrderQueryProducer orderQueryProducer;

    @Transactional
    public void save(OrderRequest request) {
        Order order = orderRepository.save(request.toEntity());

        // FeignClient를 사용하여 외부 서버와의 통신
//        orderQueryClient.save(order);

//        orderRepository.delete(order);

        // FeignClient 대신 Kafka를 사용하여 외부 서버와의 통신
        orderQueryProducer.send(order);
    }

}
