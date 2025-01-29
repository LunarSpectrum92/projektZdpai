package com.Konopka.eCommerce.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

public class KafkaConfig {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);
    private static final String BOOTSTRAP_SERVERS = "ms_kafka:9092";

    public KafkaProducer<String, String> createProducer() {
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(producerProperties);
    }

    public void createTopic(String topicName) {
        Properties adminProperties = new Properties();
        adminProperties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);

        try (AdminClient adminClient = AdminClient.create(adminProperties)) {
            if (!adminClient.listTopics().names().get().contains(topicName)) {
                NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
                adminClient.createTopics(Collections.singletonList(newTopic));
                logger.info("Topic created: " + topicName);
            } else {
                logger.info("Topic already exists: " + topicName);
            }
        } catch (Exception e) {
            logger.error("Error creating Kafka topic: " + e.getMessage());
        }
    }
}
