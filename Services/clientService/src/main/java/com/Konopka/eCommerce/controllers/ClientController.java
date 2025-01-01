package com.Konopka.eCommerce.controllers;


import com.Konopka.eCommerce.models.Photo;
import com.Konopka.eCommerce.DTO.ClientRequest;
import com.Konopka.eCommerce.models.Client;
import com.Konopka.eCommerce.models.PhotoFeign;
import com.Konopka.eCommerce.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

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
    @PostMapping("/client/photo")
    public ResponseEntity<Photo> addAvatar(@RequestParam("id") int id,@RequestParam("file") @Valid MultipartFile photo) {
        return clientService.addAvatar(id, photo);
    }


    @GetMapping("/client/photo/{id}")
    public ResponseEntity<Path> findPhotoById(@PathVariable int id){
        return clientService.findAvatarById(id);
    }





}
