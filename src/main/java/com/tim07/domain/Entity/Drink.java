package com.tim07.domain.Entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
@Entity
public class Drink extends Food implements Serializable {

    @ManyToOne
    private Restaurant restaurant;

    public Drink(){}

    public Drink(String name, String description, Double price)
    {
        super(name, description, price);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o)
    {
        Drink drink = (Drink) o;
        if(this.getName() == drink.getName() && this.getDescription() == drink.getDescription()
                && this.getPrice() == drink.getPrice())
            return true;
        return false;
    }
}
