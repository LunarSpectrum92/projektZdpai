package com.Konopka.eCommerce.services;

import com.Konopka.eCommerce.DTO.ClientUpdateRequest;
import com.Konopka.eCommerce.models.Photo;
import com.Konopka.eCommerce.DTO.ClientRequest;
import com.Konopka.eCommerce.models.Client;
import com.Konopka.eCommerce.models.PhotoFeign;
import com.Konopka.eCommerce.repositories.AddressRepository;
import com.Konopka.eCommerce.repositories.ClientRepository;
import org.hibernate.engine.spi.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


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




    public ResponseEntity<Client> createClient(ClientRequest clientRequest, Authentication authentication){
        System.out.println(
                authentication

        );
        Client client = Client.builder()
                    //.name(clientRequest.name())
                    //.surname(clientRequest.surname())
                    .phone(clientRequest.phone())
                    .keycloakId(authentication.getName())
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



    public ResponseEntity<String> findAvatarById(Integer id){
        ResponseEntity<String> path = photoFeign.findPhotoById(id);
        if(path.hasBody()){
            return new ResponseEntity<>(path.getBody(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<Client> updateClientData(ClientRequest clientRequest, Authentication authentication){
        Optional<Client> client = cr.findByKeycloakId(authentication.getName());
        if(client.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(client);

        client.get().setAddress(clientRequest.address());
        client.get().setPhone(clientRequest.phone());

        return new ResponseEntity<>(cr.save(client.get()), HttpStatus.CREATED);
    }


    public ResponseEntity<Client> deleteClientByKeycloakId(String KeycloakId){
        Optional<Client> client = cr.findByKeycloakId(KeycloakId);
        if(client.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



        cr.delete(client.get());
        return new ResponseEntity<>(client.get(), HttpStatus.OK);
    }


    public ResponseEntity<Client> getClientByKeycloakId(String KeycloakId){
        Optional<Client> client = cr.findByKeycloakId(KeycloakId);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
