package com.Konopka.eCommerce.clientService.controllers;


import com.Konopka.eCommerce.clientService.models.Client;
import com.Konopka.eCommerce.clientService.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")



public class ClientController {

    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/getClients")
    public List<Client> getClients() {
        return clientService.getClients();
    }



    @PostMapping("/createClient")
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) {
        return clientService.createClient(client);
    }









}
