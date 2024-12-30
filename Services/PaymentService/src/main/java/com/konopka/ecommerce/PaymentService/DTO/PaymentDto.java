package com.konopka.ecommerce.PaymentService.DTO;

import com.konopka.ecommerce.PaymentService.models.PaymentMethod;
import com.konopka.ecommerce.PaymentService.models.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
