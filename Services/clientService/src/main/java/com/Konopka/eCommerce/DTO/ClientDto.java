package com.Konopka.eCommerce.DTO;

import java.time.LocalDateTime;
import java.util.UUID;


public record ClientDto(
        int userId,
        String name,
        String surname,
        String phone,
        UUID keycloakId,
        LocalDateTime createdAt,
        Integer addressId,
        Integer PhotoId
) {
}
