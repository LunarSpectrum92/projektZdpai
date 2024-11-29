package com.Konopka.eCommerce.PhotoService.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.http.HttpStatusCode;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Photo {

    @Id
    @GeneratedValue()
    private int photoId;

    private String photoName;

    private String photoPath;
}
