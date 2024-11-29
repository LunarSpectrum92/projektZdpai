package com.Konopka.eCommerce.ProductService.Controllers;


import com.Konopka.eCommerce.ProductService.DTO.CategoryDto;
import com.Konopka.eCommerce.ProductService.Models.Category;
import com.Konopka.eCommerce.ProductService.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/categories")
    public List<CategoryDto> getAllCategories() {
        return categoryService.findAll();
    }


    @PostMapping("/category")
    public ResponseEntity<Integer> addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }



}
