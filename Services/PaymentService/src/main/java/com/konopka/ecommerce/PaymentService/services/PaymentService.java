package com.konopka.ecommerce.PaymentService.services;


import com.konopka.ecommerce.PaymentService.DTO.PaymentDto;
import com.konopka.ecommerce.PaymentService.DTO.PaymentMapper;
import com.konopka.ecommerce.PaymentService.kafka.PaymentProducer;
import com.konopka.ecommerce.PaymentService.models.Payment;
import com.konopka.ecommerce.PaymentService.models.Status;
import com.konopka.ecommerce.PaymentService.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PaymentService {

    PaymentRepository paymentRepository;
    PaymentProducer paymentProducer;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PaymentProducer paymentProducer) {
        this.paymentProducer = paymentProducer;
        this.paymentRepository = paymentRepository;
    }



    public ResponseEntity<Integer> createPayment(PaymentDto paymentDto) {
        Payment payment = PaymentMapper.toPayment(paymentDto);
        paymentRepository.save(payment);
        paymentProducer.producePaymentMessage(paymentDto);
        //paymentProducer.produceOrderMessage(paymentDto);
        return new ResponseEntity<>(payment.getPaymentId(), HttpStatus.CREATED);
    }




    public ResponseEntity<Status> payForOrder(Long orderId, BigDecimal amount) {
        Optional<Payment> payment = paymentRepository.findByOrderId(orderId);
        if (payment.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(payment.get().getAmount().compareTo(amount) != 0) {
            payment.get().setStatus(Status.REJECTED);
            paymentRepository.save(payment.get());
            paymentProducer.produceOrderMessage(PaymentMapper.toPaymentDto(payment.get()));
            return new ResponseEntity<>(Status.REJECTED , HttpStatus.BAD_REQUEST);
        }

        payment.get().setStatus(Status.SUCCEEDED);
        paymentRepository.save(payment.get());
        paymentProducer.produceOrderMessage(PaymentMapper.toPaymentDto(payment.get()));

        return new ResponseEntity<>(Status.SUCCEEDED, HttpStatus.OK);
    }







    public ResponseEntity<Integer> getPaymentById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.map(value -> new ResponseEntity<>(value.getPaymentId(), HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }




}