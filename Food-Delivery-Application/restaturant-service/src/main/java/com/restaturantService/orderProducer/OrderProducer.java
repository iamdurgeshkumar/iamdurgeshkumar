package com.restaturantService.orderProducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaturantService.entity.DeliveryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    // Create ObjectMapper with JavaTimeModule support
    ObjectMapper objectMapper = new ObjectMapper();

    @Value("${app.topic.name}")
    private String topicName;

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishCreateOrder(DeliveryRequest deliveryRequest) {
        try {
            String jsonString = objectMapper.writeValueAsString(deliveryRequest);
            kafkaTemplate.send(topicName, jsonString);

            log.info("Publish message to {}, with request : {}", topicName, jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
