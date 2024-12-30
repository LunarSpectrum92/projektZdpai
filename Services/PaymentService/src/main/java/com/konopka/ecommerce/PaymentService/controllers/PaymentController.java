package com.konopka.ecommerce.PaymentService.controllers;


import com.konopka.ecommerce.PaymentService.kafka.a;
import com.konopka.ecommerce.PaymentService.kafka.PaymentProducer;
import com.konopka.ecommerce.PaymentService.models.Payment;
import com.konopka.ecommerce.PaymentService.models.Status;
import com.konopka.ecommerce.PaymentService.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class PaymentController {

    PaymentService paymentService;
    @Autowired
    PaymentProducer paymentProducer;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping("/payment")
    public ResponseEntity<Integer> getPayment(@RequestParam Long id) {
        return paymentService.getPaymentById(id);
    }



    @PostMapping("/payment")
    public ResponseEntity<Status> payForPayment(@RequestBody Long orderId, @RequestBody BigDecimal amount) {
        return paymentService.payForOrder(orderId, amount);
    }




    @PostMapping("/test")
    public void test(@RequestBody a paymentDto) {
        paymentProducer.sendMessage1(paymentDto);
    }









}
