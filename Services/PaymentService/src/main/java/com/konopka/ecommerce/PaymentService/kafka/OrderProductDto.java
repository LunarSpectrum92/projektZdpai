package com.konopka.ecommerce.PaymentService.kafka;

import java.math.BigDecimal;

public record OrderProductDto(
        Integer productId,
        Integer quantity,
        BigDecimal price
) {
}
