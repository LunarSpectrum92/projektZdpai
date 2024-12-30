package com.konopka.ecommerce.orderservice.kafka;

import com.konopka.ecommerce.orderservice.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class OrderMessageProducer {

    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    public OrderMessageProducer(KafkaTemplate<String, OrderDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

//    public void produceOrderMessage(OrderDto orderDto) {
//        try {
//            log.info("Sending order message to Kafka topic: {}", orderDto);
//            kafkaTemplate.send("OrderPayment", orderDto).get();
//            log.info("Payment message successfully sent to Kafka topic");
//        } catch (Exception e) {
//            log.error("Failed to send payment message to Kafka topic: {}", e.getMessage(), e);
//            throw new RuntimeException("Error while sending payment message to Kafka", e);
//        }
//    }
}



