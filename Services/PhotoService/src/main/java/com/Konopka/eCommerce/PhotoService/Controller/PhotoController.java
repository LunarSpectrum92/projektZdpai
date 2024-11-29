package com.Konopka.eCommerce.PhotoService.Controller;


import com.Konopka.eCommerce.PhotoService.Models.Photo;
import com.Konopka.eCommerce.PhotoService.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class PhotoController {

    PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }


    @PostMapping("/photo")
    public ResponseEntity<Photo> addPhoto(@RequestParam("file") MultipartFile photo) {

        try {
            return photoService.addPhoto(photo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    @GetMapping("/photo/{id}")
    public ResponseEntity<File> findPhotoById(@PathVariable int id) {
            return photoService.findPhotoById(id);
    }





    @GetMapping("/photos")
    public ResponseEntity<Set<File>> findPhotosByIds(@RequestBody List<Integer> ids) {
        return photoService.findPhotosByIds(ids);
    }



}
