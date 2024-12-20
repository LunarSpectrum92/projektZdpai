package com.Konopka.eCommerce.clientService.DTO;


import com.Konopka.eCommerce.clientService.models.Client;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClientDtoMapper implements Function<Client, ClientDto> {


    @Override
    public ClientDto apply(Client client) {
        return new ClientDto(
                client.getUserId(),
                client.getName(),
                client.getSurname(),
                client.getPhone(),
                client.getKeycloakId(),
                client.getCreatedAt(),
                client.getAddress().getAddressId(),
                client.getPhotoId()
        );
    }
}
