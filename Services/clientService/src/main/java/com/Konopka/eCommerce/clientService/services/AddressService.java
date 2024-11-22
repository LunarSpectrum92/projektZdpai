package com.Konopka.eCommerce.clientService.services;

import com.Konopka.eCommerce.clientService.models.Address;
import com.Konopka.eCommerce.clientService.repositories.AddressRepository;
import jakarta.ws.rs.PUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }




    public ResponseEntity<Address> updateAddress(Address address) {
        if(addressRepository.findById(address.getAddressId()).isPresent()) {
            return new ResponseEntity<>(addressRepository.save(address), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






}
