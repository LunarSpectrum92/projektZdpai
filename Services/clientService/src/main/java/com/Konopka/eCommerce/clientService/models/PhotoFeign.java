package com.Konopka.eCommerce.clientService.models;


import com.Konopka.eCommerce.PhotoService.Models.Photo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

@FeignClient(name = "PHOTOSERVICE",
        url = "http://localhost:8761/api",
        configuration = FeignSupportConfig.class)
public interface PhotoFeign {


    @PostMapping(value = "/photo", consumes = "multipart/form-data")
    ResponseEntity<Photo> addPhoto(@RequestPart("file") MultipartFile photo);


    @GetMapping("/photo/{id}")
    ResponseEntity<Path> findPhotoById(@PathVariable int id);

//
//    @GetMapping("/test")
//    String test();


//
//
//    @GetMapping("/photos")
//    ResponseEntity<Set<File>> findPhotosByIds(@RequestBody List<Integer> ids);




}
