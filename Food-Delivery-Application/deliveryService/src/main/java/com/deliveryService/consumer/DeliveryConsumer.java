package com.deliveryService.consumer;

import com.deliveryService.dto.DeliveryRequest;
import com.deliveryService.service.DeliveryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeliveryConsumer {

    @Autowired
    DeliveryService deliveryService;

    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "${app.topic.name}", groupId = "delivery_group")
    public void consumeOrder(String message) {
        try {
            DeliveryRequest request = objectMapper.readValue(message, DeliveryRequest.class);
            log.info("Message Received with message : {}", request);

            deliveryService.create(request);

            log.info("Delivery created for orderId : {}", request.getOrderId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
