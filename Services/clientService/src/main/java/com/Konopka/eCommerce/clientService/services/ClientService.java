package com.Konopka.eCommerce.clientService.services;

import com.Konopka.eCommerce.clientService.models.Client;
import com.Konopka.eCommerce.clientService.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClientService {

    private final ClientRepository cr;

    @Autowired
    public ClientService(ClientRepository cr) {
        this.cr = cr;
    }


    public List<Client> getClients(){
        return cr.findAll();
    }

//
//    public Client getClient(Integer id){
//        return cr.findById(id).orElseThrow( new ClientNotFoundException("klient o id" + id + "nie został znaleziony"));
//    }

//    public Client getClient(Integer id){
//        return cr.findById(id).get();
//    }





    public ResponseEntity<Client> createClient(Client client){
        if(cr.findById(client.getUserId()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
            return new ResponseEntity<>(cr.save(client), HttpStatus.CREATED);

    }


}
