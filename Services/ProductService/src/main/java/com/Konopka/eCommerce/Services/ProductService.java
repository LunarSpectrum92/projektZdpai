package com.Konopka.eCommerce.Services;


import com.Konopka.eCommerce.DTO.PhotoDto;
import com.Konopka.eCommerce.models.Category;
import com.Konopka.eCommerce.models.Photo;
import com.Konopka.eCommerce.DTO.ProductDto;
import com.Konopka.eCommerce.DTO.ProductDtoMapper;
import com.Konopka.eCommerce.models.PhotoFeign;
import com.Konopka.eCommerce.models.Product;
import com.Konopka.eCommerce.Repository.CategoryRepo;
import com.Konopka.eCommerce.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

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


    //sorting prices asc
    public List<ProductDto> findAllByOrderByPriceAsc(){
        return productRepo.findAllByOrderByPriceAsc().stream()
                .map(productDtoMapper)
                .toList();
    }

    //sorting prices asc
    public List<ProductDto> findAllByOrderByPriceDesc(){
        return productRepo.findAllByOrderByPriceDesc().stream()
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

    //update product
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
                        .category(categoryRepo.findAllById(productDto.category()))
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


    public ResponseEntity<Set<Integer>> addPhotos(PhotoDto photoDto) {
        Optional<Product> product = productRepo.findById(photoDto.productId());
        if (product.isEmpty() || photoDto.photos() == null || photoDto.photos().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Set<Integer> photosIds = new HashSet<>();


        for(MultipartFile photo : photoDto.photos()){
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


    public ResponseEntity<List<ProductDto>> getProductsByCategory(Integer id){

        Optional<Category> category = categoryRepo.findById(id);
        if(category.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Product> products = productRepo.findAllByCategory(id);
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products.stream().map(productDtoMapper).toList(), HttpStatus.OK);
    }






    public ResponseEntity<List<ProductDto>> findAllByBrand(String brand){
        List<Product> products = productRepo.findAllByBrand(brand);
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products.stream().map(productDtoMapper).toList(), HttpStatus.OK);
    }

    public ResponseEntity<List<ProductDto>> searchEngine(String prompt){
        final String promptFinal = prompt.toLowerCase();
        var filteredProducts = productRepo.findAll()
                .stream()
                .filter(product -> product.getProductName().toLowerCase().contains(promptFinal) || product.getBrand().toLowerCase().contains(promptFinal) || product.getDescription().toLowerCase().contains(promptFinal))
                .map(productDtoMapper)
                .toList();
        return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
    }



    public Set<String> getBrands(){
        return productRepo.findAll().stream()
                .map((product) -> product.getBrand())
                .collect(Collectors.toSet());
    }

    public ResponseEntity<List<ProductDto>> getProductsForCategoryAndSubcategories(int categoryId) {
        final List<ProductDto> productsByCategory = productRepo.findAllProductsForCategoryAndSubcategories(categoryId).stream()
                .map(productDtoMapper)
                .toList();
        if(productsByCategory.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productsByCategory, HttpStatus.OK);
    }


    public ResponseEntity<ProductDto> findProductById(int id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product = productOptional.get();
        return new ResponseEntity<>(productDtoMapper.apply(product), HttpStatus.OK);
    }



    //TODO
    //update product 1
    //sort products by price 1
    //find product by category1
    //find product by brand1
    //find product by description1



}
