package com.Konopka.eCommerce.ProductService.Services;


import com.Konopka.eCommerce.PhotoService.Models.Photo;
import com.Konopka.eCommerce.ProductService.DTO.ProductDto;
import com.Konopka.eCommerce.ProductService.DTO.ProductDtoMapper;
import com.Konopka.eCommerce.ProductService.Models.PhotoFeign;
import com.Konopka.eCommerce.ProductService.Models.Product;
import com.Konopka.eCommerce.ProductService.Repository.CategoryRepo;
import com.Konopka.eCommerce.ProductService.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.*;

@Service
public class ProductService {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    private ProductDtoMapper productDtoMapper;
    private PhotoFeign photoFeign;

    @Autowired
    public ProductService(ProductRepo productRepo, ProductDtoMapper productDto, CategoryRepo categoryRepo, PhotoFeign photoFeign) {
        this.productRepo = productRepo;
        this.productDtoMapper = productDto;
        this.categoryRepo = categoryRepo;
        this.photoFeign = photoFeign;
    }


    public List<ProductDto> GetProducts(){
        return productRepo.findAll().stream()
                .map(productDtoMapper)
                .toList();
    }


    //adding products to order
    public Optional<ProductDto> getProductById(int id, int quantity) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product not found with ID: " + id);
        }

        Product product = productOptional.get();
        if (quantity > product.getQuantity()) {
            throw new RuntimeException("Insufficient stock for product ID: " + id);
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepo.save(product);


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
            product.setBrand(productDto.brand());
            product.setQuantity(productDto.quantity());
            product.setDiscount(productDto.discount());
            product.setCategory(categoryRepo.findAllById(productDto.category()));

            return new ResponseEntity<>(
                    productRepo.save(product)
                    , HttpStatus.CREATED
            );
        }
    }


    public ResponseEntity<ProductDto> updateProduct(ProductDto productDto){
        if(productRepo.findById(productDto.productId()).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product = Product.builder()
                        .productId(productDto.productId())
                        .productName(productDto.productName())
                        .description(productDto.description())
                        .brand(productDto.brand())
                        .price(productDto.price())
                        .quantity(productDto.quantity())
                        .discount(productDto.discount())
                        .build();

        productRepo.save(product);


        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }


    public ResponseEntity<Integer> deleteProduct(int id){
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<Set<Integer>> addPhotos(Set<MultipartFile> photos, Integer id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isEmpty() || photos == null || photos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Set<Integer> photosIds = new HashSet<>();


        for(MultipartFile photo : photos){
            ResponseEntity<Photo> response = photoFeign.addPhoto(photo);
            if ((response == null) || (response.getBody() == null)) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            photosIds.add(response.getBody().getPhotoId());
        }

        product.get().setPhotoIds(new ArrayList<>(photosIds));
        productRepo.save(product.get());
        return new ResponseEntity<>(photosIds, HttpStatus.CREATED);
    }


    public ResponseEntity<Set<Path>> getPhotos(List<Integer> ids){
        ResponseEntity<Set<Path>> response = photoFeign.findPhotosByIds(ids);
        if (response == null || response.getBody() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Set<Path> paths = response.getBody();
        return new ResponseEntity<>(paths, HttpStatus.OK);
    }



    public List<ProductDto> GetProductsById(List<Integer> ids){
        return productRepo.findAllById(ids).stream()
                .map(productDtoMapper)
                .toList();
    }



}
