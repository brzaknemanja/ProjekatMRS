package com.tim07.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tim07.domain.Enumeration.KitchenType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Size(min = 2)
    private String name;

    @Column(nullable = false)
    @NotNull
    @Size(min = 2)
    private String description;

    @Column(nullable = false)
    @NotNull
    @Size(min = 2)
    private String street;

    @Column(nullable = false)
    @NotNull
    @Size(min = 2)
    private String city;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private KitchenType kitchenType;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Dish> dishes;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Drink> drinks;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("restaurant")
    private List<ManagerRestaurant> managers;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("restaurant")
    private List<Barman> barmen;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("restaurant")
    private List<Chef> chefs;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("restaurant")
    private List<Waiter> waiters;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<RestaurantTable> tables;

    public Restaurant(){}

    public Restaurant(String name, String description, String street, String city, KitchenType kitchenType){
        this.name = name;
        this.description = description;
        this.street = street;
        this.city = city;
        this.kitchenType = kitchenType;
        this.managers = new ArrayList<>();
        this.barmen = new ArrayList<>();
        this.chefs = new ArrayList<>();
        this.waiters = new ArrayList<>();
        this.dishes = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.tables = new ArrayList<>();
    }

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }

    public String getDescription(){ return description; }

    public void setDescription(String description){ this.description = description; }

    public List<Dish> getDishes(){ return dishes; }

    public void setDishes(List<Dish> dishes) { this.dishes = dishes;}

    public List<Drink> getDrinks() { return drinks;}

    public void setDrinks(List<Drink> drinks) {this.drinks = drinks;}

    public List<ManagerRestaurant> getManagers() {return managers;}

    public void setManagers(List<ManagerRestaurant> managers) {this.managers = managers;}

    public List<Barman> getBarmen() {return barmen;}

    public void setBarmen(List<Barman> barmen) {this.barmen = barmen;}

    public List<Chef> getChefs() { return chefs;}

    public void setChefs(List<Chef> chefs) {this.chefs = chefs;}

    public List<Waiter> getWaiters() {return waiters;}

    public void setWaiters(List<Waiter> waiters) {this.waiters = waiters;}

    public List<RestaurantTable> getTables() {return tables;}

    public void setTables(List<RestaurantTable> tables) {this.tables = tables;}

    public String getStreet() {return street;}

    public void setStreet(String street) {this.street = street;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public KitchenType getKitchenType() {return kitchenType;}

    public void setKitchenType(KitchenType kitchenType) {this.kitchenType = kitchenType;}

    @Override
    public boolean equals(Object o)
    {
        Restaurant restaurant = (Restaurant)o;
        if(this.name == restaurant.getName() && this.description == restaurant.getDescription())
            return true;
        return false;
    }
}
