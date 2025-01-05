package com.Konopka.eCommerce.controllers;


import com.Konopka.eCommerce.models.Address;
import com.Konopka.eCommerce.services.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }



    @PutMapping("/adres")
    public ResponseEntity<Address> updateAddress(@Valid @RequestBody Address address) {
        return addressService.updateAddress(address);
    }





}
