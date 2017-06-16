package com.tim07.domain.Entity;

import com.tim07.domain.Enumeration.UserType;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Katarina Cukurov on 15/04/2017.
 */
@Entity
public class ManagerRestaurant extends User implements Serializable {

    @Column(nullable = false)
    @NotNull
    @Email
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    public ManagerRestaurant(){}

    public ManagerRestaurant(String username, String password, UserType type, String name, String lastname, String email)
    {
        super(name, lastname, username, password, type);
        this.email = email;
    }

    public ManagerRestaurant(String username, String password, UserType type, String name, String lastname, String email, Restaurant restaurant)
    {
        super(name, lastname, username, password, type);
        this.email = email;
        this.restaurant = restaurant;
    }


    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    public Restaurant getRestaurant() { return restaurant;}

    public void setRestaurant(Restaurant restaurant) {this.restaurant = restaurant;}

    @Override
    public boolean equals(Object manager)
    {
        ManagerRestaurant managerRestaurant = (ManagerRestaurant) manager;
        if((this.getName() == managerRestaurant.getName()) && (this.getLastname() == managerRestaurant.getLastname())
                && (this.email == managerRestaurant.getEmail()) && (this.getUsername() == managerRestaurant.getUsername())
                && (this.getPassword() == managerRestaurant.getPassword()) && (this.getType() == managerRestaurant.getType())){
            return true;
        }
        return false;
    }
}
