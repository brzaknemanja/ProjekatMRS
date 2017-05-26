package com.tim07.domain.DTO;

import java.util.List;

/**
 * Created by Katarina Cukurov on 30/04/2017.
 */
public class RestaurantManagerSystemViewDTO {

    private String name;
    private String description;
    private String street;
    private String city;
    private List<ManagerRestaurantDTO> managers;

    public RestaurantManagerSystemViewDTO(){}

    public RestaurantManagerSystemViewDTO(String name, String description, String street, String city, List<ManagerRestaurantDTO> managers) {
        this.name = name;
        this.description = description;
        this.street = street;
        this.city = city;
        this.managers = managers;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public String getStreet() {return street;}

    public void setStreet(String street) {this.street = street;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public List<ManagerRestaurantDTO> getManagers() {return managers;}

    public void setManagers(List<ManagerRestaurantDTO> managers) {this.managers = managers;}
}
