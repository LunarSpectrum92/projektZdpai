package com.Konopka.eCommerce.DTO;

import com.Konopka.eCommerce.models.Address;

public record ClientUpdateRequest(
        Integer userId,
        String phone,
        Address address
) {
}
