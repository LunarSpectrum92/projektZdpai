package com.Konopka.eCommerce.kafka;

import java.math.BigDecimal;

public record OrderProductDto(
        Integer productId,
        Integer quantity,
        BigDecimal price
) {
}
