package com.konopka.ecommerce.orderservice.dto;

import com.konopka.ecommerce.orderservice.models.OrderProduct;
import com.konopka.ecommerce.orderservice.models.PaymentMethods;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderRequest(
        String clientId,
        List<OrderProductDto> orderProductsList,
        PaymentMethods paymentMethods
) {
}
