package com.example.ordercommand.kafka;

import com.example.ordercommand.domain.entity.Order;
import com.example.ordercommand.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class OrderQueryProducer {

    private final KafkaTemplate<String, Order> kafkaTemplate;
    private final OrderRepository orderRepository;

    @Async
    public void send(Order order) {
        CompletableFuture<SendResult<String, Order>> resultCompletableFuture =
                kafkaTemplate.send(TopicConfig.ORDER_QUERY, order);

        resultCompletableFuture.thenAccept(result -> orderRepository.deleteById(order.getId()));
    }

}
