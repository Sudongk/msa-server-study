package com.example.ceo.kafka;

import com.example.ceo.domain.request.OwnerRequest;
import com.example.ceo.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerConsumer {

    private final OwnerService ownerService;

    @KafkaListener(topics = TopicConfig.OWNER_TOPIC)
    public void listen(OwnerRequest request) {
        ownerService.save(request);
    }

}
