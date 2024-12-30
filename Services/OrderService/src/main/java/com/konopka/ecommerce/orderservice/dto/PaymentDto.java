package com.konopka.ecommerce.orderservice.dto;

import com.konopka.ecommerce.orderservice.models.PaymentMethods;
import com.konopka.ecommerce.orderservice.models.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PaymentDto(
        Integer orderId,
        Integer customerId,
        BigDecimal amount,
        String paymentMethod,
        String status,
        List<Integer> createdAt,
        List<Integer> updatedAt
) {
}
