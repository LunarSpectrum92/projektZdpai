package com.konopka.ecommerce.orderservice.services;


import com.Konopka.eCommerce.ProductService.Models.Product;
import com.Konopka.eCommerce.clientService.models.Client;
import com.konopka.ecommerce.orderservice.dto.OrderDto;
import com.konopka.ecommerce.orderservice.dto.OrderDtoMapper;
import com.konopka.ecommerce.orderservice.dto.OrderProductDto;
import com.konopka.ecommerce.orderservice.dto.OrderRequest;
import com.konopka.ecommerce.orderservice.kafka.OrderMessageProducer;
import com.konopka.ecommerce.orderservice.models.*;
import com.konopka.ecommerce.orderservice.repositories.OrderProductRepository;
import com.konopka.ecommerce.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;


@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;
    private OrderMessageProducer orderMessageProducer;
    private ClientFeign clientFeign;
    private ProductFeign productFeign;

    @Autowired
    public OrderService(OrderRepository orderRepository,ClientFeign clientFeign,ProductFeign productFeign, OrderProductRepository orderProductRepository, OrderMessageProducer orderMessageProducer) {
        this.orderRepository = orderRepository;
        this.clientFeign = clientFeign;
        this.productFeign = productFeign;
        this.orderProductRepository = orderProductRepository;
        this.orderMessageProducer = orderMessageProducer;
    }




    public ResponseEntity<OrderDto> createOrder(OrderRequest orderRequest) {

        if (orderRequest == null || orderRequest.orderProductsList() == null || orderRequest.orderProductsList().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BigDecimal amount = BigDecimal.ZERO;

        //verify client
        ResponseEntity<Client> client = clientFeign.getClient(Integer.parseInt(orderRequest.clientId()));
        if(!client.hasBody()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //verify products and quantity
        for(OrderProductDto orderProductDto : orderRequest.orderProductsList()){
            var product = productFeign.findById(orderProductDto.productId(), orderProductDto.quantity());
            if(product.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("Error-Message", "Order not found")
                        .build();
            }
            //count total amount
            amount = amount.add(orderProductDto.price());


            try {
                orderProductRepository.save(OrderDtoMapper.mapOrderProductToEntity(orderProductDto));
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        //create order
        Order order = Order.builder()
                .totalAmount(amount)
                .clientId(String.valueOf(client.getBody().getUserId()))
                .orderProductsList(OrderDtoMapper.mapOrderProductsToEntity(orderRequest.orderProductsList()))
                .paymentMethods(orderRequest.paymentMethods())
                .status(Status.WAITING_FOR_PAYMENT)
                .build();




        try {
            Order savedOrder = orderRepository.save(order);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //send message to payment
        //orderMessageProducer.produceOrderMessage(OrderDtoMapper.toDto(order));

        return new ResponseEntity<>(OrderDtoMapper.toDto(order), HttpStatus.CREATED);
    }









}
