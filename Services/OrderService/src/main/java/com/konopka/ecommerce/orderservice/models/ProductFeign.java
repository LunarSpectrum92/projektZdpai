package com.konopka.ecommerce.orderservice.models;


import com.Konopka.eCommerce.ProductService.DTO.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@FeignClient(name = "PRODUCTSERVICE",
        url = "http://localhost:9020/api")
public interface ProductFeign {

    @GetMapping("/product")
    Optional<ProductDto> findById(@RequestParam int id, @RequestParam int quantity);

    @GetMapping("/products")
    List<ProductDto> GetProductsById(@RequestBody List<Integer> ids);


}
