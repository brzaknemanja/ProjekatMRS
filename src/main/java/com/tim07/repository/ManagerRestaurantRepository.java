package com.tim07.repository;

import com.tim07.domain.DAO.ManagerRestaurantDAO;
import com.tim07.domain.Entity.ManagerRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Katarina Cukurov on 15/04/2017.
 */
public interface ManagerRestaurantRepository extends JpaRepository<ManagerRestaurant, Long> {

    ManagerRestaurant save(ManagerRestaurant managerRestaurant);

    ManagerRestaurant findByUsername(String username);

    @Query("SELECT r.name AS name, r.lastname AS lastname, r.username AS username " +
            "FROM ManagerRestaurant r WHERE r.restaurant is null")
    List<ManagerRestaurantDAO> findAllManagers();
}
