package com.ecommerce.productservice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    public ProductEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendProductEvent(String message) {
        kafkaTemplate.send("product-events", message);
    }
}
