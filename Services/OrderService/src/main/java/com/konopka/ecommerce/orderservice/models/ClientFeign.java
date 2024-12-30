package com.konopka.ecommerce.orderservice.models;




import com.Konopka.eCommerce.clientService.models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CLIENTSERVICE",
        url = "http://localhost:9010/api")
public interface ClientFeign {

    @PostMapping("/client")
     ResponseEntity<Client> getClient(@RequestBody Integer id);


    @GetMapping("/test")
    String test();


}
