package com.konopka.ecommerce.orderservice.dto;

import com.konopka.ecommerce.orderservice.models.Order;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public record OrderProductDto(
        Integer productId,
        Integer quantity,
        BigDecimal price
) {
}
