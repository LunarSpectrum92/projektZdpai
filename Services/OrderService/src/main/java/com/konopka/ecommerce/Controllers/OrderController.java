package com.Konopka.eCommerce.Controllers;


import com.Konopka.eCommerce.DTO.OrderDto;
import com.Konopka.eCommerce.DTO.OrderRequest;
import com.Konopka.eCommerce.models.ClientFeign;
import com.Konopka.eCommerce.services.OrderService;
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


//    @GetMapping("/ordera")
//    public String createOrder() {
//        return clientFeign.test();
//    }


}
