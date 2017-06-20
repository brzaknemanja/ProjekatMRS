package com.tim07.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * Created by brzak on 17.6.17..
 */
@Entity
public class TableOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "tableOrder", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("tableOrder")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    @ManyToOne
    @JsonIgnore
    private RestaurantTable restaurantTable;

    public TableOrder() {}

    public TableOrder(List<OrderItem> orderItems, Restaurant restaurant, RestaurantTable restaurantTable) {
        this.orderItems = orderItems;
        this.restaurant = restaurant;
        this.restaurantTable = restaurantTable;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantTable getRestaurantTable() {
        return restaurantTable;
    }

    public void setRestaurantTable(RestaurantTable restaurantTable) {
        this.restaurantTable = restaurantTable;
    }
}
