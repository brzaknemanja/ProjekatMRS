package com.tim07.domain.DTO;

import com.tim07.domain.Entity.Drink;

/**
 * Created by Katarina Cukurov on 30/04/2017.
 */
public class FoodDTO {

    private String name;

    private String description;

    private Double price;

    public FoodDTO(){}

    public FoodDTO(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public Double getPrice() {return price;}

    public void setPrice(Double price) {this.price = price;}
}
