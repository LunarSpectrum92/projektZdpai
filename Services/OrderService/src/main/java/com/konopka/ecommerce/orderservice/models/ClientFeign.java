package com.konopka.ecommerce.orderservice.models;




import com.Konopka.eCommerce.clientService.models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CLENTSERVICE",
        url = "http://localhost:8761/api")
public interface ClientFeign {

    @GetMapping("/client")
     ResponseEntity<Client> getClient(@RequestBody int id);




}
