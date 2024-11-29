package com.Konopka.eCommerce.ProductService.Controllers;


import com.Konopka.eCommerce.ProductService.DTO.ProductDto;
import com.Konopka.eCommerce.ProductService.Models.Product;
import com.Konopka.eCommerce.ProductService.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }




    @GetMapping("/products")
    public List<ProductDto> findAllOrderByPriceAsc(){
        return productService.findAllByOrderByPriceAsc();
    }




    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }
    



}
