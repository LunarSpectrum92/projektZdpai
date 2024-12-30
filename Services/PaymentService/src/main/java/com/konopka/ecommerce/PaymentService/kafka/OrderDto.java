package com.konopka.ecommerce.PaymentService.kafka;

import com.konopka.ecommerce.PaymentService.models.PaymentMethod;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;




public record OrderDto(
        Integer orderId,

        BigDecimal totalAmount,

        LocalDateTime orderDate,

        String clientId,

        List<OrderProductDto> orderProductsList,

        PaymentMethods paymentMethods
) {
}
