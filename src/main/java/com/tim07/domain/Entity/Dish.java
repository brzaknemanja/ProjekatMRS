package com.tim07.domain.Entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
@Entity
public class Dish extends Food implements Serializable {

    @ManyToOne
    private Restaurant restaurant;

    public Dish(){}

    public Dish(String name, String description, Double price)
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
        Dish dish = (Dish)o;
        if(this.getName() == dish.getName() && this.getDescription() == dish.getDescription() &&
                this.getPrice() == dish.getPrice())
            return true;
        return false;
    }
}
