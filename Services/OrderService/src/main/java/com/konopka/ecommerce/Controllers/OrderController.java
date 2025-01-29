package com.Konopka.eCommerce.Controllers;


import com.Konopka.eCommerce.DTO.OrderDto;
import com.Konopka.eCommerce.DTO.OrderRequest;
import com.Konopka.eCommerce.models.ClientFeign;
import com.Konopka.eCommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //create order
    @PostMapping("/order")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }


    //getorder
    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Integer orderId) {
        return orderService.getOrder(orderId);
    }

    //getallorders
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return orderService.getAllOrders();
    }

    //getOrdersByClientId
    @GetMapping("/order/client/{clientId}")
    public ResponseEntity<List<OrderDto>> getOrdersByClientId(@PathVariable String clientId) {
        return orderService.getOrdersByClientId(clientId);
    }



//    @GetMapping("/ordera")
//    public String createOrder() {
//        return clientFeign.test();
//    }


}
