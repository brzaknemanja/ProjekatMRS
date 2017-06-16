package com.tim07.domain.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tim07.domain.Enumeration.UserType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by freez on 10-Apr-17.
 */
@Entity
public class Waiter extends Employee implements Serializable
{
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

    @OneToMany(mappedBy = "waiter", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("waiter")
    private List<TableSegment> tableSegments;

    public Waiter() {
        tableSegments = new ArrayList<>();
    }

    public Waiter(String username, String password, UserType type, String name, String lastname,
                  String dateOfBirdth, int dressSize, int shoeSize) {
        super(name, lastname, username, password, type);
        this.dateOfBirth = dateOfBirdth;
        this.dressSize = dressSize;
        this.shoeSize = shoeSize;
        this.tableSegments = new ArrayList<>();
    }

    public Waiter(String username, String password, UserType type, String name, String lastname,
                  String dateOfBirdth, int dressSize, int shoeSize, Restaurant restaurant) {
        super(name, lastname, username, password, type);
        this.dateOfBirth = dateOfBirdth;
        this.dressSize = dressSize;
        this.shoeSize = shoeSize;
        this.restaurant = restaurant;
        this.tableSegments = new ArrayList<>();
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBith) {
        this.dateOfBirth = dateOfBith;
    }

    public Integer getDressSize() {
        return dressSize;
    }

    public void setDressSize(Integer dressSize) {
        this.dressSize = dressSize;
    }

    public Integer getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(Integer shoeSize) {
        this.shoeSize = shoeSize;
    }

    public Restaurant getRestaurant() { return restaurant;}

    public void setRestaurant(Restaurant restaurant) {this.restaurant = restaurant;}

    public List<TableSegment> getTableSegments() {
        return tableSegments;
    }

    public void setTableSegments(List<TableSegment> tableSegments) {
        this.tableSegments = tableSegments;
    }

    @Override
    public boolean equals(Object regUserObj) {
        Waiter regUser = (Waiter)regUserObj;
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
