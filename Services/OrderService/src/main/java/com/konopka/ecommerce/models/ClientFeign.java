package com.Konopka.eCommerce.models;




import com.Konopka.eCommerce.models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CLIENTSERVICE",
        url = "http://localhost:9010/api/clients")
public interface ClientFeign {

    @GetMapping("/client/{userId}")
     ResponseEntity<Client> getClient(@PathVariable Integer userId);


    @GetMapping("/client/keycloak/{keycloakId}")
    ResponseEntity<Client> getClientByKeycloakId(@PathVariable String keycloakId);



//    @GetMapping("/test")
//    String test();


}
