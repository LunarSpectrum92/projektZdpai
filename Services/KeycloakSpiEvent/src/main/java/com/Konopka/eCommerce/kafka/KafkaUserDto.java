package com.Konopka.eCommerce.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaUserDto {

    private String userId;
    private String phone;
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String flatNumber;
    private String postalCode;


    public String toJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.err.println("Error converting DTO to JSON: " + e.getMessage());
            throw new RuntimeException("Error converting DTO to JSON", e);
        }
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
