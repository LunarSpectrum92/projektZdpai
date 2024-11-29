package com.Konopka.eCommerce.ProductService.Services;

import com.Konopka.eCommerce.ProductService.DTO.CategoryDto;
import com.Konopka.eCommerce.ProductService.DTO.CategoryDtoMapper;
import com.Konopka.eCommerce.ProductService.Models.Category;
import com.Konopka.eCommerce.ProductService.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    CategoryRepo categoryRepo;
    CategoryDtoMapper categoryDtoMapper;


    @Autowired
    public CategoryService(CategoryRepo categoryRepo, CategoryDtoMapper categoryDtoMapper) {
        this.categoryRepo = categoryRepo;
        this.categoryDtoMapper = categoryDtoMapper;
    }




    public Optional<CategoryDto> findById(int id) {
        return Optional.of(categoryDtoMapper.apply(categoryRepo.findById(id).get()));
    }


    public List<CategoryDto> findAll() {
        return categoryRepo.findAll().stream()
                .map(categoryDtoMapper)
                .collect(Collectors.toList());
    }




    public ResponseEntity<Integer> createCategory(CategoryDto categoryDto) {
        if (categoryRepo.findById(categoryDto.categoryId()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(categoryDto.categoryParentId() != null){
            Category category = Category.builder()
                    .categoryId(categoryDto.categoryId())
                    .categoryName(categoryDto.categoryName())
                    .categoryParentId(categoryRepo.findById(categoryDto.categoryParentId()).get())
                    .build();
        }

        Category category = Category.builder()
                .categoryId(categoryDto.categoryId())
                .categoryName(categoryDto.categoryName())
                .build();

        categoryRepo.save(category);

        return new ResponseEntity<>(category.getCategoryId(), HttpStatus.CREATED);
    }

    public ResponseEntity<Integer> DeleteCategory(CategoryDto categoryDto) {
        if(categoryRepo.findById(categoryDto.categoryId()).isPresent()){
            categoryRepo.deleteById(categoryDto.categoryId());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    public ResponseEntity<CategoryDto> updateCategory(CategoryDto categoryDto) {
        if (categoryRepo.findById(categoryDto.categoryId()).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Category category = Category.builder()
                        .categoryId(categoryDto.categoryId())
                        .categoryName(categoryDto.categoryName())
                        .categoryParentId(categoryRepo.findById(categoryDto.categoryParentId()).get())
                        .build();


        categoryRepo.save(category);

        return new ResponseEntity<>(
                categoryDto
                ,HttpStatus.CONFLICT);
    }





}
