package com.sirine.AlphaVantage.config;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class AlphaVantageKafkaProducer {
    @Value("${spring.kafka.bootstrap-servers}")
        private String bootstrapServers;

    private final AlphaVantageFetcher alphaVantageFetcher;

    public AlphaVantageKafkaProducer(AlphaVantageFetcher alphaVantageFetcher) {
        this.alphaVantageFetcher = alphaVantageFetcher;
    }
    private static final String KAFKA_TOPIC = "alphavantage-stream";


    @Bean
        public Map<String, Object> producerConfig() {
            Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class.getName());
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class.getName());

            return props;
        }   @Bean
        public ProducerFactory<String,String> producerFactory(){
            return new DefaultKafkaProducerFactory<>(producerConfig());
        }
        @Bean
        public KafkaTemplate<String,String> kafkaTemplate(
                ProducerFactory<String,String> producerFactory
        ){
            return  new KafkaTemplate<>(producerFactory);
        }



}

