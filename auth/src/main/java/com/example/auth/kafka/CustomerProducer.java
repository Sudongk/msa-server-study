package com.example.auth.kafka;

import com.example.auth.client.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerProducer {

    private final KafkaTemplate<String, UserRequest> kafkaTemplate;

    @Async
    public void send(UserRequest request) {
        kafkaTemplate.send(TopicConfig.CUSTOMER_TOPIC, request);
    }

}
