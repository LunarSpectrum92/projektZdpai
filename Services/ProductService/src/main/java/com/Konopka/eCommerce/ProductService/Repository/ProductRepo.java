package com.Konopka.eCommerce.ProductService.Repository;


import com.Konopka.eCommerce.ProductService.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

Optional<Product> findByproductName(String name);

List<Product> findAllByOrderByPriceAsc();




}
