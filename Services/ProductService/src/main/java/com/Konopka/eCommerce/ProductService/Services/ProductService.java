package com.Konopka.eCommerce.ProductService.Services;


import com.Konopka.eCommerce.ProductService.DTO.ProductDto;
import com.Konopka.eCommerce.ProductService.DTO.ProductDtoMapper;
import com.Konopka.eCommerce.ProductService.Models.Product;
import com.Konopka.eCommerce.ProductService.Repository.CategoryRepo;
import com.Konopka.eCommerce.ProductService.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    private ProductDtoMapper productDtoMapper;

    @Autowired
    public ProductService(ProductRepo productRepo, ProductDtoMapper productDto, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.productDtoMapper = productDto;
        this.categoryRepo = categoryRepo;
    }


    public List<ProductDto> GetProducts(){
        return productRepo.findAll().stream()
                .map(productDtoMapper)
                .toList();
    }


    //adding products to order
    public Optional<ProductDto> getProductsById(int id, int quantity) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product not found with ID: " + id);
        }

        Product product = productOptional.get();
        if (quantity > product.getQuantity()) {
            throw new RuntimeException("Insufficient stock for product ID: " + id);
        }

        ProductDto productDto = productDtoMapper.apply(product);
        return Optional.of(productDto);
    }



    //finding products in search engine
    public Optional<ProductDto> GetProductsByName(String productName) {
        Optional<Product> product = productRepo.findByproductName(productName);
        if (product.isEmpty()) {
            throw new RuntimeException("Product not found with name: " + productName);
        }

        Product product1 = product.get();

        return Optional.of(productDtoMapper.apply(product1));
    }



    public List<ProductDto> findAllByOrderByPriceAsc(){
        return productRepo.findAllByOrderByPriceAsc().stream()
                .map(productDtoMapper)
                .toList();
    }


    public ResponseEntity<Product> createProduct(ProductDto productDto){
        if(productRepo.findById(productDto.productId()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }else{
            Product product = new Product();
            product.setProductName(productDto.productName());
            product.setPrice(productDto.price());
            product.setDescription(productDto.description());
            product.setQuantity(productDto.quantity());
            product.setDiscount(productDto.discount());
            if(categoryRepo.findAllById(productDto.category()).isEmpty()){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            product.setCategory(categoryRepo.findAllById(productDto.category()));

            return new ResponseEntity<>(
                    productRepo.save(product)
                    , HttpStatus.CREATED
            );
        }
    }

    public void updateProduct(Product product){
        productRepo.findById(product.getProductId())
                .ifPresent(
                        user1 -> {user1.setProductName(product.getProductName());
                        productRepo.save(user1);}
                );

    }












}
