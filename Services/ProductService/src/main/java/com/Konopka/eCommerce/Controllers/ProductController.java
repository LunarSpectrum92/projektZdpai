package com.Konopka.eCommerce.Controllers;


import com.Konopka.eCommerce.DTO.ProductDto;
import com.Konopka.eCommerce.models.PhotoFeign;
import com.Konopka.eCommerce.models.Product;
import com.Konopka.eCommerce.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    ProductService productService;
    PhotoFeign photoFeign;

    @Autowired
    public ProductController(ProductService productService, PhotoFeign photoFeign) {
        this.productService = productService;
        this.photoFeign = photoFeign;
    }


    @GetMapping("/products")
    public List<ProductDto> GetProductsById(@RequestBody List<Integer> ids){
        return productService.GetProductsById(ids);
    }


    @GetMapping("/product")
    public Optional<ProductDto> findById(@RequestParam int id, @RequestParam int quantity){
        return productService.getProductById(id, quantity);
    }

    @GetMapping("/product/name")
    public Optional<ProductDto> findByName(@RequestParam String name){
        return productService.GetProductsByName(name);
    }


    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }

    @PostMapping("/product/photos")
    public ResponseEntity<Set<Integer>> createPhotos(@RequestParam("file") Set<MultipartFile> photos,@RequestParam("id") Integer id){
        return productService.addPhotos(photos, id);
    }

    @GetMapping("/product/photos")
    public ResponseEntity<Set<Path>> getPhotos(List<Integer> ids){
        return productService.getPhotos(ids);
    }

}
