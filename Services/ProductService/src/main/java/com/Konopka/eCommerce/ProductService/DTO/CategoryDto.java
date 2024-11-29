package com.Konopka.eCommerce.ProductService.DTO;



public record CategoryDto(
        int categoryId,
        String categoryName,
        Integer categoryParentId
) {
}
