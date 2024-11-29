package com.Konopka.eCommerce.ProductService.Repository;

import com.Konopka.eCommerce.ProductService.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    @NativeQuery("SELECT * FROM category WHERE category_parent_id IS NULL")
    List<Category> findAllNestedCategories();


}
