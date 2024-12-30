package com.konopka.ecommerce.orderservice.Controllers;


import com.konopka.ecommerce.orderservice.dto.OrderDto;
import com.konopka.ecommerce.orderservice.dto.OrderRequest;
import com.konopka.ecommerce.orderservice.models.ClientFeign;
import com.konopka.ecommerce.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {


    private final OrderService orderService;
    @Autowired
    private ClientFeign clientFeign;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }




    @PostMapping("/order")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }


    @GetMapping("/ordera")
    public String createOrder() {
        return clientFeign.test();
    }


}
