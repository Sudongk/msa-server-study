package com.example.pay.kafka;

import com.example.pay.domain.kafka.OrderKafkaData;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCommandProducer {

    private final KafkaTemplate<String, OrderKafkaData> kafkaTemplate;

    @Async
    public void send(OrderKafkaData orderKafkaData) {
        kafkaTemplate.send(TopicConfig.ORDER_COMMAND, orderKafkaData);
    }

}
