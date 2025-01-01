package com.Konopka.eCommerce.DTO;


import com.Konopka.eCommerce.models.Payment;

public class PaymentMapper {

    public static PaymentDto toPaymentDto(Payment payment) {
        return new PaymentDto(
                payment.getOrderId(),
                payment.getCustomerId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }

    public static Payment toPayment(PaymentDto paymentDto) {
        return Payment.builder()
                .orderId(paymentDto.orderId())
                .customerId(paymentDto.customerId())
                .status(paymentDto.status())
                .amount(paymentDto.amount())
                .paymentMethod(paymentDto.paymentMethod())
                .createdAt(paymentDto.createdAt())
                .updatedAt(paymentDto.updatedAt())
                .build();
    }






}
