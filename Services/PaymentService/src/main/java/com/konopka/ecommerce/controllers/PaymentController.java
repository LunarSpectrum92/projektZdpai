package com.Konopka.eCommerce.controllers;


import com.Konopka.eCommerce.DTO.PaymentRequest;
import com.Konopka.eCommerce.kafka.PaymentProducer;
import com.Konopka.eCommerce.kafka.a;

import com.Konopka.eCommerce.models.Status;
import com.Konopka.eCommerce.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    PaymentService paymentService;



    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping("/payment/{id}")
    public ResponseEntity<Integer> getPayment(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }



    @PostMapping("/payment")
    public ResponseEntity<Status> payForPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.payForOrder(paymentRequest);
    }



//
//    @PostMapping("/test")
//    public void test(@RequestBody a paymentDto) {
//        paymentProducer.sendMessage1(paymentDto);
//    }









}
