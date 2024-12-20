package com.Konopka.eCommerce.ProductService.DTO;

import com.Konopka.eCommerce.ProductService.Models.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.List;

public record ProductDto (
        int productId,
        String productName,
        String description,
        String brand,
        Double price,
        Integer quantity,
        List<Integer> category,
        Integer discount
) {
}
