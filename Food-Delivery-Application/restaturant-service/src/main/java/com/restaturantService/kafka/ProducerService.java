//package com.restaturantService.kafka;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ProducerService {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    @Value("${app.topic.name}")
//    private String topicName;
//
//    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void send(String message) {
//        kafkaTemplate.send(topicName, message);
//    }
//}
