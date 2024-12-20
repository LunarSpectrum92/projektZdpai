package com.Konopka.eCommerce.clientService.DTO;

import com.Konopka.eCommerce.clientService.models.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

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
