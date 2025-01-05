package com.Konopka.eCommerce.DTO;

import java.time.LocalDateTime;


public record ClientDto(
        int userId,
        String name,
        String surname,
        String phone,
        String keycloakId,
        LocalDateTime createdAt,
        Integer addressId,
        Integer PhotoId
) {
}
