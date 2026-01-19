//package com.restaturantService.kafka;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/kafka")
//public class KafkaController {
//    private final ProducerService producer;
//
//    public KafkaController(ProducerService producer) {
//
//        this.producer = producer;
//    }
//
//    @PostMapping("/publish")
//    public ResponseEntity<String> publish(@RequestParam String message) {
//        producer.send(message);
//        return ResponseEntity.ok("Message sent to Kafka");
//    }
//}
