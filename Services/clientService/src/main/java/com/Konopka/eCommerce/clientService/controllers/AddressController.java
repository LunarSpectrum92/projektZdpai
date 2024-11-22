package com.Konopka.eCommerce.clientService.controllers;


import com.Konopka.eCommerce.clientService.models.Address;
import com.Konopka.eCommerce.clientService.services.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }



    @PutMapping("/updateAddress")
    public ResponseEntity<Address> updateAddress(@Valid @RequestBody Address address) {
        return addressService.updateAddress(address);
    }





}
