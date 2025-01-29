package com.Konopka.eCommerce.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record CreateProductDto(
        int productId,
        String productName,
        String description,
        String brand,
        Double price,
        Integer quantity,
        List<Integer> category,
        Integer discount,
        List<MultipartFile> photoS
) {
}
