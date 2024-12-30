package com.Konopka.eCommerce.clientService.controllers;


import com.Konopka.eCommerce.PhotoService.Models.Photo;
import com.Konopka.eCommerce.clientService.DTO.ClientRequest;
import com.Konopka.eCommerce.clientService.models.Client;
import com.Konopka.eCommerce.clientService.models.PhotoFeign;
import com.Konopka.eCommerce.clientService.services.ClientService;
import feign.Headers;
import feign.RequestLine;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ClientController {

    ClientService clientService;
    private PhotoFeign photoFeign;



    @Autowired
    public ClientController(ClientService clientService, PhotoFeign photoFeign) {
        this.clientService = clientService;
        this.photoFeign = photoFeign;
    }


    @GetMapping("/getClients")
    public List<Client> getClients() {
        return clientService.getClients();
    }



    @PostMapping("/client")
    public ResponseEntity<Client> getClient(@RequestBody Integer userId) {
        return clientService.getClientById(userId);
    }


    @PostMapping("/createClient")
    public ResponseEntity<Client> createClient(@Valid @RequestBody ClientRequest client) {
        return clientService.createClient(client);
    }



    //value = "/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    @PostMapping("/photo")
    public ResponseEntity<Photo> addAvatar(@RequestParam("id") int id,@RequestParam("file") @Valid MultipartFile photo) {
        return clientService.addAvatar(id, photo);
    }



    @GetMapping("/test")
    public String test() {
        return "test";
    }



    @GetMapping("/photo/{id}")
    public ResponseEntity<Path> findPhotoById(@PathVariable int id){
        return clientService.findAvatarById(id);
    }





}
