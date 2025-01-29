package com.Konopka.eCommerce.DTO;


import com.Konopka.eCommerce.models.Address;

public record ClientRequest(
//        String name,
//        String surname,
        String phone,
        Address address
) {
}

