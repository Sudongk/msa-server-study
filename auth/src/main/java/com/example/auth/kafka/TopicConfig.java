package com.example.auth.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    public static final String CUSTOMER_TOPIC = "CUSTOMER";
    public static final String OWNER_TOPIC = "OWNER";

    @Bean
    public NewTopic customerTopic() {
        return new NewTopic(CUSTOMER_TOPIC, 1, (short) 1);
    }

    @Bean
    public NewTopic ownerTopic() {
        return TopicBuilder
                .name(OWNER_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

}
