package com.Konopka.eCommerce.DTO;

import java.math.BigDecimal;

public record OrderProductDto(
        Integer productId,
        Integer quantity,
        BigDecimal price
) {
}
