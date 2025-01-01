package com.Konopka.eCommerce.kafka;

import com.Konopka.eCommerce.DTO.OrderDto;
import com.Konopka.eCommerce.DTO.PaymentDto;
import com.Konopka.eCommerce.repositories.PaymentRepository;
import com.Konopka.eCommerce.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentConsument {

    PaymentRepository paymentRepository;
    PaymentService paymentService;

    @Autowired
    public PaymentConsument(PaymentRepository paymentRepository, PaymentService paymentService) {
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }


    @KafkaListener(topics = "OrderToPayment", groupId = "notifications-group")
    public void consumePayment(OrderDto orderDto) {

        System.out.println(orderDto.toString());


        PaymentDto paymentDto = new PaymentDto(
                orderDto.orderId(),
                null,
                orderDto.totalAmount(),
                orderDto.paymentMethod(),
                null,
                null,
                null
        );

        paymentService.createPayment(paymentDto);

    }




}
