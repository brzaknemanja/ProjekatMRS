package com.tim07.domain.DTO.updateDTO;

import com.tim07.domain.Enumeration.KitchenType;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
public class RestaurantRegistrationOrUpdateDTO {

    private String name;

    private String description;

    private String username;

    private String street;

    private String city;

    private KitchenType kitchenType;

    public RestaurantRegistrationOrUpdateDTO(){}

    public RestaurantRegistrationOrUpdateDTO(String name, String description, String username, String street, String city, KitchenType kitchenType) {
        this.name = name;
        this.description = description;
        this.username = username;
        this.street = street;
        this.city = city;
        this.kitchenType = kitchenType;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getStreet() {return street;}

    public void setStreet(String street) {this.street = street;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public KitchenType getKitchenType() {return kitchenType;}

    public void setKitchenType(KitchenType kitchenType) {this.kitchenType = kitchenType;}
}
