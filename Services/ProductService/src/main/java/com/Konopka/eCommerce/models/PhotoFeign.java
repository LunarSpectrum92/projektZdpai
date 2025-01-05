package com.Konopka.eCommerce.models;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

@FeignClient(name = "PHOTOSERVICE",
        url = "http://localhost:9030/api/photos",
        configuration = FeignSupportConfig.class)
public interface PhotoFeign {


    @PostMapping(value = "/photo", consumes = "multipart/form-data")
    ResponseEntity<Photo> addPhoto(@RequestPart("file") MultipartFile photo);


    @GetMapping("/photo/{id}")
    ResponseEntity<Path> findPhotoById(@PathVariable int id);


    @GetMapping("/photos")
    ResponseEntity<Set<Path>> findPhotosByIds(@RequestBody List<Integer> ids);

}