package com.Konopka.eCommerce.DTO;


import com.Konopka.eCommerce.models.PaymentMethod;
import com.Konopka.eCommerce.models.Status;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Builder
public record PaymentDto(
        Integer orderId,
        Integer customerId,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
        ) {
}
