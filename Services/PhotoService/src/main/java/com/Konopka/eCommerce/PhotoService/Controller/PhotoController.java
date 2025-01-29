package com.Konopka.eCommerce.PhotoService.Controller;


import com.Konopka.eCommerce.models.Photo;
import com.Konopka.eCommerce.PhotoService.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    //name = "/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    @PostMapping("/photo")
    public ResponseEntity<Photo> addPhoto(@RequestParam("file") MultipartFile photo) {
        String contentType = photo.getContentType();
        System.out.println(contentType);
        try {
            return photoService.addPhoto(photo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @GetMapping("/photo/{id}")
    public ResponseEntity<Path> findPhotoById(@PathVariable int id) {
            return photoService.findPhotoById(id);
    }


    @GetMapping("/photos")
    public ResponseEntity<Set<Path>> findPhotosByIds(@RequestParam List<Integer> ids) {
        return photoService.findPhotosByIds(ids);
    }



}
