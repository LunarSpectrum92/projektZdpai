package com.Konopka.eCommerce.services;

import com.Konopka.eCommerce.models.Address;
import com.Konopka.eCommerce.repositories.AddressRepository;
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
