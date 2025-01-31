package com.Konopka.eCommerce.services;


import com.Konopka.eCommerce.DTO.OrderDto;
import com.Konopka.eCommerce.DTO.OrderDtoMapper;
import com.Konopka.eCommerce.DTO.OrderProductDto;
import com.Konopka.eCommerce.DTO.OrderRequest;
import com.Konopka.eCommerce.kafka.OrderMessageProducer;
import com.Konopka.eCommerce.models.*;
import com.Konopka.eCommerce.repositories.OrderProductRepository;
import com.Konopka.eCommerce.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
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
        ResponseEntity<Client> client = clientFeign.getClientByKeycloakId(orderRequest.clientId());
        if(!client.hasBody()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<OrderProduct> productDtos = new ArrayList<>();
        //verify products and quantity
        for(OrderProductDto orderProductDto : orderRequest.orderProductsList()){
            var product = productFeign.findById(orderProductDto.productId(), orderProductDto.quantity());
            if(product.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("Error-Message", "Product not found")
                        .build();
            }
            //count total amount
            amount = amount.add(orderProductDto.price());
            System.out.println(amount);

            productDtos.add(OrderDtoMapper.mapOrderProductToEntity(orderProductDto));

        }

        //create order
        Order order = Order.builder()
                .totalAmount(amount)
                .clientId(String.valueOf(client.getBody().getUserId()))
                .orderProductsList(OrderDtoMapper.mapOrderProductsToEntity(orderRequest.orderProductsList()))
                //.paymentMethod(orderRequest.paymentMethod())
                .status(Status.WAITING_FOR_PAYMENT)
                .build();




        try {
            Order savedOrder = orderRepository.save(order);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        for(OrderProduct op : productDtos){
            op.setOrder(order);
            try {
                orderProductRepository.save(op);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


        //send message to payment
        orderMessageProducer.produceOrderMessage(OrderDtoMapper.toDto(order));

        return new ResponseEntity<>(OrderDtoMapper.toDto(order), HttpStatus.CREATED);
    }


    //TODO
    //deleteOrder



    //getorder
    public ResponseEntity<OrderDto> getOrder(Integer orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.map(value -> new ResponseEntity<>(OrderDtoMapper.toDto(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //getallorders
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        System.out.println("Liczba zamówień: " + orders.size());

        orders.forEach(order -> log.info("Order ID: {}", order.getOrderProductsList()));

        List<OrderDto> orderDtos = orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate))
                .map(OrderDtoMapper::toDto)
                .toList();

        return new ResponseEntity<>(orderDtos,  HttpStatus.OK);
    }

    //getOrdersByClientId
    public ResponseEntity<List<OrderDto>> getOrdersByClientId(String clientId) {
        List<Order> orders = orderRepository.findAllByClientId(clientId);
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



        List<OrderDto> orderDtos = orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate))
                .map(OrderDtoMapper::toDto)
                .toList();
        return new ResponseEntity<>(orderDtos,  HttpStatus.OK);
    }


}
