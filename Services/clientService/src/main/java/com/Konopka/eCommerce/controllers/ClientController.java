package com.Konopka.eCommerce.controllers;


import com.Konopka.eCommerce.models.Photo;
import com.Konopka.eCommerce.DTO.ClientRequest;
import com.Konopka.eCommerce.models.Client;
import com.Konopka.eCommerce.models.PhotoFeign;
import com.Konopka.eCommerce.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    ClientService clientService;



    @Autowired
    public ClientController(ClientService clientService, PhotoFeign photoFeign) {
        this.clientService = clientService;
    }


    @GetMapping("/clients")
    public List<Client> getClients() {
        return clientService.getClients();
    }



    @GetMapping("/client/{userId}")
    public ResponseEntity<Client> getClient(@PathVariable Integer userId) {
        return clientService.getClientById(userId);
    }



    @GetMapping("/test")
    public String getClient(Authentication authentication) {
        return authentication.getName();
    }





    @PostMapping("/client")
    public ResponseEntity<Client> createClient(@Valid @RequestBody ClientRequest client) {
        return clientService.createClient(client);
    }



    //value = "/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    @PostMapping("/client/photo")
    public ResponseEntity<Photo> addAvatar(@RequestParam("id") int id,@RequestParam("file") @Valid MultipartFile photo) {
        return clientService.addAvatar(id, photo);
    }


    @GetMapping("/client/photo/{id}")
    public ResponseEntity<Path> findPhotoById(@PathVariable int id){
        return clientService.findAvatarById(id);
    }





}
