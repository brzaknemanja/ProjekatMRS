package com.tim07.repository;

import com.tim07.domain.DAO.RestaurantViewInListDAO;
import com.tim07.domain.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

    Restaurant save(Restaurant restaurant);

    Restaurant findByName(String name);

    Restaurant findById(Long id);

    @Query("SELECT r.id AS id, r.name AS name, r.description AS description, r.street AS street," +
            "r.city AS city, r.kitchenType as kitchenType FROM Restaurant r")
    List<RestaurantViewInListDAO> getAllRestaurants();
}
