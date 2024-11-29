package com.Konopka.eCommerce.ProductService.DTO;


import com.Konopka.eCommerce.ProductService.Models.Category;
import com.Konopka.eCommerce.ProductService.Models.Product;
import com.Konopka.eCommerce.ProductService.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductDtoMapper implements Function<Product, ProductDto> {

    ProductRepo productRepo;

    @Autowired
    public ProductDtoMapper(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }





    @Override
    public ProductDto apply(Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().stream()
                        .map(
                                Category::getCategoryId
                        )
                        .collect(Collectors.toList()),
                product.getDiscount()
                );
    }
}
