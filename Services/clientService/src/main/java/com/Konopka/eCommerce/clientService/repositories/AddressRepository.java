package com.Konopka.eCommerce.clientService.repositories;

import com.Konopka.eCommerce.clientService.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
