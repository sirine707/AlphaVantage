package com.sirine.AlphaVantage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AlphaVantageKafkaProducerService {

    private final AlphaVantageFetcher alphaVantageFetcher;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String KAFKA_TOPIC = "alphavantage-stream";

    @Autowired
    public AlphaVantageKafkaProducerService(AlphaVantageFetcher alphaVantageFetcher, KafkaTemplate<String, String> kafkaTemplate) {
        this.alphaVantageFetcher = alphaVantageFetcher;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToKafka(String message) {
        kafkaTemplate.send(KAFKA_TOPIC, message);
    }

    @Scheduled(fixedRate = 60000)  // every 1 minute
    public void pollAlphaVantageAndProduceToKafka() {
        String response = alphaVantageFetcher.fetchDataFromAlphaVantage();
        sendToKafka(response);
    }
}

