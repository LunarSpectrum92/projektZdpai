package com.Konopka.eCommerce.DTO;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public record ProductResponse(
        int productId,
        String productName,
        String description,
        String brand,
        Double price,
        Integer quantity,
        List<Integer> category,
        Integer discount,
        Set<Path> photoPaths
) {
}
