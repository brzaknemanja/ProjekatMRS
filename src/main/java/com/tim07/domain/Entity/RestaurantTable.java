package com.tim07.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tim07.domain.Enumeration.Segment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Katarina Cukurov on 22/04/2017.
 */
@Entity
public class RestaurantTable implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private Integer chairNumber;

    @Column(nullable = false)
    @NotNull
    private Double top;

    @Column(nullable = false)
    @NotNull
    private Double left;

    @Column(nullable = false)
    @NotNull
    private Double rotation;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Segment segment;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "restaurantTable", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("restaurantTable")
    private List<TableOrder> tableOrderList;

    public RestaurantTable(){}

    public RestaurantTable(String name,Integer chairNumber, Double top, Double left, Double rotation, Segment segment) {
        this.name = name;
        this.chairNumber = chairNumber;
        this.top = top;
        this.left = left;
        this.rotation = rotation;
        this.segment = segment;
    }

    public Long getId() {
        return id;
    }

    public Integer getChairNumber() { return chairNumber;}

    public void setChairNumber(Integer chairNumber) {this.chairNumber = chairNumber;}

    public Double getTop() {return top;}

    public void setTop(Double top) {this.top = top;}

    public Double getLeft() {return left;}

    public void setLeft(Double left) {this.left = left;}

    public Double getRotation() {return rotation;}

    public void setRotation(Double rotation) {this.rotation = rotation;}

    public Segment getSegment() {return segment;}

    public void setSegment(Segment segment) {this.segment = segment;}

    public Restaurant getRestaurant() {return restaurant;}

    public void setRestaurant(Restaurant restaurant) {this.restaurant = restaurant;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TableOrder> getTableOrderList() {
        return tableOrderList;
    }

    public void setTableOrderList(List<TableOrder> tableOrderList) {
        this.tableOrderList = tableOrderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantTable table = (RestaurantTable) o;

        if (chairNumber != null ? !chairNumber.equals(table.chairNumber) : table.chairNumber != null) return false;
        if (top != null ? !top.equals(table.top) : table.top != null) return false;
        if (left != null ? !left.equals(table.left) : table.left != null) return false;
        if (rotation != null ? !rotation.equals(table.rotation) : table.rotation != null) return false;
        if (segment != table.segment) return false;
        return restaurant != null ? restaurant.equals(table.restaurant) : table.restaurant == null;
    }

    @Override
    public String toString() {
        return "RestaurantTable{" +
                "id=" + id +
                ", chairNumber=" + chairNumber +
                ", top=" + top +
                ", left=" + left +
                ", rotation=" + rotation +
                ", segment=" + segment +
                ", restaurant=" + restaurant +
                '}';
    }
}
