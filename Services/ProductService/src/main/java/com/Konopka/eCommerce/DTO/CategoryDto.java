package com.Konopka.eCommerce.DTO;



public record CategoryDto(
        int categoryId,
        String categoryName,
        Integer categoryParentId
) {
}
