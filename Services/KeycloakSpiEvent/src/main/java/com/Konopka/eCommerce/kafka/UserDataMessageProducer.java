package com.Konopka.eCommerce.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class UserDataMessageProducer {

    private final KafkaProducer<String, String> producer;

    public UserDataMessageProducer(KafkaProducer<String, String> producer) {
        this.producer = producer;
    }

    public void produceOrderMessage(String topicName, String message) {
        try {
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, message);
            producer.send(record).get();
            System.out.println("Message sent to Kafka: " + message);
        } catch (Exception e) {
            System.err.println("Error sending message to Kafka: " + e.getMessage());
        }
    }

    public void close() {
        if (producer != null) {
            producer.close();
            System.out.println("Kafka producer closed.");
        }
    }
}
