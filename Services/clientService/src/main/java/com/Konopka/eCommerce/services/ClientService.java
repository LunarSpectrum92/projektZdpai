package com.Konopka.eCommerce.services;

import com.Konopka.eCommerce.models.Photo;
import com.Konopka.eCommerce.DTO.ClientRequest;
import com.Konopka.eCommerce.models.Client;
import com.Konopka.eCommerce.models.PhotoFeign;
import com.Konopka.eCommerce.repositories.AddressRepository;
import com.Konopka.eCommerce.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;


@Service
public class ClientService {

    private final ClientRepository cr;
    private final AddressRepository addressRepository;
    private final PhotoFeign photoFeign;

    @Autowired
    public ClientService(ClientRepository cr, PhotoFeign photoFeign, AddressRepository addressRepository) {
        this.cr = cr;
        this.addressRepository = addressRepository;
        this.photoFeign = photoFeign;
    }


    public List<Client> getClients(){
        return cr.findAll();
    }



    public ResponseEntity<Client> getClientById(int id){
        Optional<Client> client = cr.findById(id);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }




    public ResponseEntity<Client> createClient(ClientRequest clientRequest){
            Client client = Client.builder()
                    .name(clientRequest.name())
                    .surname(clientRequest.surname())
                    .phone(clientRequest.phone())
                    .address(clientRequest.address())
                    .build();
            return new ResponseEntity<>(cr.save(client), HttpStatus.CREATED);

    }


    public ResponseEntity<Photo> addAvatar(int id, MultipartFile photo) {
        Optional<Client> clientOptional = cr.findById(id);

        if (clientOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Client client = clientOptional.get();

        ResponseEntity<Photo> photoResponse = photoFeign.addPhoto(photo);
        if (photoResponse.getBody() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        client.setPhotoId(photoResponse.getBody().getPhotoId());
        cr.save(client);

        return new ResponseEntity<>(photoResponse.getBody(), HttpStatus.CREATED);
    }



    public ResponseEntity<Path> findAvatarById(Integer id){
        ResponseEntity<Path> path = photoFeign.findPhotoById(id);
        if(path.hasBody()){
            return new ResponseEntity<>(path.getBody(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }






}
