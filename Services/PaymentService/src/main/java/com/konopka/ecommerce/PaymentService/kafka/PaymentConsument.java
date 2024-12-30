package com.konopka.ecommerce.PaymentService.kafka;

import com.konopka.ecommerce.PaymentService.DTO.PaymentDto;
import com.konopka.ecommerce.PaymentService.repositories.PaymentRepository;
import com.konopka.ecommerce.PaymentService.services.PaymentService;
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

//
//    @KafkaListener(topics = "OrderPayment", groupId = "notifications-group")
//    public void consumePayment(OrderDto orderDto) {
//
////        System.out.println(orderDto.toString());
//
//
//
//
////        PaymentDto paymentDto = new PaymentDto(
////                orderDto.orderId(),
////                Integer.parseInt(orderDto.clientId()),
////                orderDto.totalAmount(),
////                orderDto.paymentMethods(),
////                null,
////                null,
////                null
////        );
////
////        paymentService.createPayment(paymentDto);
//
//    }




}
