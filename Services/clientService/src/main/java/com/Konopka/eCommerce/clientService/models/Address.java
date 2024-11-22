package com.Konopka.eCommerce.clientService.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String flatNumber;

    @Pattern(regexp = "\\d{2}-\\d{3}", message = "Kod pocztowy musi mieć format XX-XXX")
    private String postalCode;

}
