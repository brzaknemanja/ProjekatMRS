package com.tim07.service;

import com.tim07.domain.DAO.RestaurantViewInListDAO;
import com.tim07.domain.Entity.*;
import com.tim07.domain.Enumeration.KitchenType;
import com.tim07.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ManagerRestaurantRepository managerRestaurantRepository;

    @Autowired
    TableRepository tableRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    DishRepository dishRepository;

    @Override
    public void createNewRestaurant(String name, String description, String street, String city, KitchenType kitchenType,
                                    ManagerRestaurant manager)
    {
        Restaurant restaurant = new Restaurant(name, description, street, city, kitchenType);
        restaurant.getManagers().add(manager);
        manager.setRestaurant(restaurant);

        this.restaurantRepository.save(restaurant);
        this.managerRestaurantRepository.save(manager);

    }

    @Override
    public boolean createNewTables(List<RestaurantTable> tables){
        for (RestaurantTable table: tables) {
            this.tableRepository.save(table);
        }
        return true;
    }

    @Override
    public boolean createNewDrink(Drink drink){
        this.drinkRepository.save(drink);
        return true;
    }

    @Override
    public boolean createNewDish(Dish dish){
        this.dishRepository.save(dish);
        return true;
    }

    @Override
    public Restaurant getByName(String name){
        return this.restaurantRepository.findByName(name);
    }

    @Override
    public Restaurant getById(Long id) { return this.restaurantRepository.findById(id);}

    @Override
    public void updateName(Restaurant restaurant){
        this.restaurantRepository.save(restaurant);
    }

    @Override
    public void updateDescription(Restaurant restaurant){
        this.restaurantRepository.save(restaurant);
    }

    @Override
    public void updateStreet(Restaurant restaurant){ this.restaurantRepository.save(restaurant);}

    @Override
    public void updateCity(Restaurant restaurant){
        this.restaurantRepository.save(restaurant);
    }

    @Override
    public List<RestaurantViewInListDAO> getAllRestaurants(){
        return this.restaurantRepository.getAllRestaurants();
    }
    @Override
    public void addNewManager(Restaurant restaurant){this.restaurantRepository.save(restaurant); }
}
