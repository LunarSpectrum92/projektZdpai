package com.Konopka.eCommerce.PhotoService.service;


import com.Konopka.eCommerce.PhotoService.Models.Photo;
import com.Konopka.eCommerce.PhotoService.Repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PhotoService {



    private static final String FilePath = "C:\\Users\\JK\\Documents\\projekty\\projektZdpai\\Services\\PhotoService\\src\\main\\java\\com\\Konopka\\eCommerce\\PhotoService\\photos";
    PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }


    public ResponseEntity<Photo> addPhoto(MultipartFile photoFile) throws IOException {
        if(photoFile.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        File file = new File(FilePath + File.separator + photoFile.getOriginalFilename());

        if(!Objects.equals(file.getParent(), FilePath)){
            throw new SecurityException("wrong file");
        }

        if (!Files.probeContentType(file.toPath()).equals("image/jpeg") &&
                !Files.probeContentType(file.toPath()).equals("image/png")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Files.copy(photoFile.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);


        Photo photo = Photo.builder()
                .photoPath(FilePath + File.separator + photoFile.getOriginalFilename())
                .photoName(photoFile.getOriginalFilename())
                .build();
        photoRepository.save(photo);
        return new ResponseEntity<>(photo, HttpStatus.CREATED);
    }



    public ResponseEntity<File> findPhotoById(Integer photoId) {
        if(photoRepository.findById(photoId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Photo> photo = photoRepository.findById(photoId);
        File file = new File(photo.get().getPhotoPath());


        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    public ResponseEntity<Set<File>> findPhotosByIds(List<Integer> photoIds) {
        List<Photo> photo = photoRepository.findAllById(photoIds);
        if(photo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Set<File> files = new HashSet<>();

        Set<String> photos = photo.stream()
                .map(Photo::getPhotoPath)
                .collect(Collectors.toSet());

        for(String photoUrl : photos){
            files.add(new File(photoUrl));
        }

        return new ResponseEntity<>(files, HttpStatus.OK);
    }





}
