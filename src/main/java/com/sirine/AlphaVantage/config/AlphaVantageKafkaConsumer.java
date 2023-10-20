package com.sirine.AlphaVantage.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AlphaVantageKafkaConsumer {

    private static final String KAFKA_TOPIC = "alphavantage-stream";

    @KafkaListener(topics = KAFKA_TOPIC, groupId = "group_id")
    public void consume(String message) {
        System.out.println(message);
        // or further process the message or send it to some other service or frontend
    }
}

