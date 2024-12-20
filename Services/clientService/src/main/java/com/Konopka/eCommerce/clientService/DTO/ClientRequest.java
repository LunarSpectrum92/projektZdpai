package com.Konopka.eCommerce.clientService.DTO;


import com.Konopka.eCommerce.clientService.models.Address;

public record ClientRequest(
        String name,
        String surname,
        String phone,
        Address address
) {
}

