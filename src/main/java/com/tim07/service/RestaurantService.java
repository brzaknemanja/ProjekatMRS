package com.tim07.service;

import com.tim07.domain.DAO.RestaurantViewInListDAO;
import com.tim07.domain.Entity.*;
import com.tim07.domain.Enumeration.KitchenType;

import java.util.List;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
public interface RestaurantService {

    void createNewRestaurant(String name, String description, String street, String city, KitchenType kitchenType,
                             ManagerRestaurant managerRestaurant);

    boolean createNewTables(List<RestaurantTable> tables);

    void addNewManager(Restaurant restaurant);

    boolean createNewDrink(Drink drink);

    boolean createNewDish(Dish dish);

    Restaurant getByName(String name);

    Restaurant getById(Long id);

    void updateName(Restaurant restaurant);

    void updateDescription(Restaurant restaurant);

    void updateStreet(Restaurant restaurant);

    void updateCity(Restaurant restaurant);

    List<RestaurantViewInListDAO> getAllRestaurants();
}
