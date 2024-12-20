package com.konopka.ecommerce.orderservice.repositories;

import com.konopka.ecommerce.orderservice.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
}
