package com.tim07.domain.Entity;

import com.tim07.domain.Enumeration.UserType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */

@Entity
public class Chef extends Employee implements Serializable{

    @Column(nullable = false)
    @NotNull
    private String dateOfBirth;

    @Column(nullable = false)
    @NotNull
    private Integer dressSize;

    @Column(nullable = false)
    @NotNull
    private Integer shoeSize;

    @ManyToOne
    private Restaurant restaurant;

    public Chef(){}

    public Chef(String username, String password, UserType type, String name, String lastname, String dateOfBirth,
                Integer dressSize, Integer shoeSize)
    {
        super(name, lastname, username, password, type);
        this.dateOfBirth = dateOfBirth;
        this.dressSize = dressSize;
        this.shoeSize = shoeSize;
    }

    public String getDateOfBirth(){ return dateOfBirth; }

    public void setDateOfBirth(String dateOfBirth){ this.dateOfBirth = dateOfBirth; }

    public Integer getDressSize(){ return dressSize; }

    public void setDressSize(Integer dressSize){ this.dressSize = dressSize; }

    public Integer getShoeSize(){ return shoeSize; }

    public void setShoeSize(Integer shoeSize){ this.shoeSize = shoeSize; }

    public Restaurant getRestaurant() { return restaurant;}

    public void setRestaurant(Restaurant restaurant) {this.restaurant = restaurant;}

    @Override
    public boolean equals(Object regUserObj) {
        Chef regUser = (Chef)regUserObj;
        if(this.getName() == regUser.getName() && this.getLastname() == regUser.getLastname()
                && this.getUsername() == regUser.getUsername()
                && this.getPassword() == regUser.getPassword()
                && this.getType() == regUser.getType()
                && this.getDateOfBirth() == regUser.getDateOfBirth()
                && this.getDressSize() == regUser.getDressSize()
                && this.getShoeSize() == regUser.getShoeSize())
        return true;
        return false;
    }
}
