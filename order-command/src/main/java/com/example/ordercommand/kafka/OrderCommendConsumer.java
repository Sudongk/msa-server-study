package com.example.ordercommand.kafka;

import com.example.ordercommand.domain.request.OrderRequest;
import com.example.ordercommand.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCommendConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = TopicConfig.ORDER_COMMAND)
    public void listen(OrderRequest request) {
        orderService.save(request);
    }

}
