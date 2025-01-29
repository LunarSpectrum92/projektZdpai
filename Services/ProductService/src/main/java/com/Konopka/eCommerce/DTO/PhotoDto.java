package com.Konopka.eCommerce.DTO;

import com.Konopka.eCommerce.models.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public record PhotoDto(
        Integer productId,
        Set<MultipartFile> photos
) {
}
