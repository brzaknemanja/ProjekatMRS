package com.tim07.domain.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private String description;

    @Column(nullable = false)
    @NotNull
    private Double price;

    public Food(){}

    public Food(String name, String description, Double price)
    {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName(){ return name;}

    public void setName(String name){ this.name = name; }

    public String getDescription(){ return description; }

    public void setDescription(String description){ this.description = description; }

    public Double getPrice(){ return price; }

    public void setPrice(Double price){this.price = price;}
}
