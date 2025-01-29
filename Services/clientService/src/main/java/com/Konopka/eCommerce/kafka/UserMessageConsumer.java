package com.Konopka.eCommerce.kafka;


import com.Konopka.eCommerce.models.Address;
import com.Konopka.eCommerce.models.Client;
import com.Konopka.eCommerce.repositories.ClientRepository;
import com.Konopka.eCommerce.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserMessageConsumer {

    private final ClientRepository clientRepository;
    private final ClientService clientService;

    @Autowired
    public UserMessageConsumer(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @KafkaListener(topics = "userData", groupId = "user-group")
    public void consumePayment(String message) {
        try {
        KafkaUserDto kafkaUserDto = new ObjectMapper().readValue(message, KafkaUserDto.class);
        Address address = Address.builder()
                .city(kafkaUserDto.getCity())
                .country(kafkaUserDto.getCountry())
                .street(kafkaUserDto.getStreet())
                .houseNumber(kafkaUserDto.getHouseNumber())
                .flatNumber(kafkaUserDto.getFlatNumber())
                .postalCode(kafkaUserDto.getPostalCode())
                .build();

        Client client = Client.builder()
                .phone(kafkaUserDto.getPhone())
                .address(address)
                .keycloakId(kafkaUserDto.getUserId())
                .build();
        clientRepository.save(client);
        log.info("Received Client: {}", client);
    } catch (Exception e) {
        log.error("Error consuming Kafka message: {}", e.getMessage());
    }
    }




}

