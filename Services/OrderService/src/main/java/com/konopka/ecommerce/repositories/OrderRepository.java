package com.Konopka.eCommerce.repositories;


import com.Konopka.eCommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByClientId(String clientId);

}
