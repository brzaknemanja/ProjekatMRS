package com.tim07.service;

import com.tim07.domain.DAO.ManagerRestaurantDAO;
import com.tim07.domain.Entity.ManagerRestaurant;

import java.util.List;

/**
 * Created by Katarina Cukurov on 15/04/2017.
 */
public interface ManagerRestaurantService {

    boolean createNewManagerRestaurant(ManagerRestaurant managerRestaurant);

    ManagerRestaurant getByUsername(String username);

    List<ManagerRestaurantDAO> getAllManagers();
}
