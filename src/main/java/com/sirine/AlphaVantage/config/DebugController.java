package com.sirine.AlphaVantage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

    @Autowired
    private AlphaVantageKafkaProducerService AlphaVantageKafkaProducerService;

    @GetMapping("/trigger")
    public ResponseEntity<String> triggerFetchAndSend() {
        AlphaVantageKafkaProducerService.pollAlphaVantageAndProduceToKafka();
        return ResponseEntity.ok("Triggered!");
    }
}

