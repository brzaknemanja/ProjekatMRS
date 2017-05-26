package com.tim07.domain.DTO;

import com.tim07.domain.Entity.Restaurant;

import java.util.List;

/**
 * Created by Katarina Cukurov on 22/04/2017.
 */
public class TablesDTO {

    private List<TableDTO> tables;
    private Restaurant restaurant;

    public TablesDTO(){}

    public TablesDTO(Restaurant restaurant, List<TableDTO> tables ) {
        this.tables = tables;
        this.restaurant = restaurant;
    }

    public List<TableDTO> getTables() {return tables;}

    public void setTables(List<TableDTO> tables) {this.tables = tables;}

    public Restaurant getRestaurant() {return restaurant;}

    public void setRestaurant(Restaurant restaurant) {this.restaurant = restaurant;}

    @Override
    public String toString() {
        return "TablesDTO{" +
                "tables=" + tables +
                ", restaurant=" + restaurant +
                '}';
    }
}
