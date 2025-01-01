package com.Konopka.eCommerce.DTO;


import com.Konopka.eCommerce.models.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer orderId,
        Integer customerId,
        BigDecimal amount,
        PaymentMethod paymentMethod
) {
}
