package com.tim07.service;

import com.tim07.domain.DAO.ManagerRestaurantDAO;
import com.tim07.domain.Entity.ManagerRestaurant;
import com.tim07.repository.ManagerRestaurantRepository;
import com.tim07.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Katarina Cukurov on 15/04/2017.
 */
@Service
public class ManagerRestaurantServiceImpl implements ManagerRestaurantService{

    @Autowired
    private ManagerRestaurantRepository managerRestaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createNewManagerRestaurant(ManagerRestaurant managerRestaurant)
    {
        if(this.userRepository.findByUsername(managerRestaurant.getUsername()) == null)
        {
            this.managerRestaurantRepository.save(managerRestaurant);
            return true;
        }
        return false;
    }

    @Override
    public ManagerRestaurant getByUsername(String username)
    {
        return this.managerRestaurantRepository.findByUsername(username);
    }

    @Override
    public List<ManagerRestaurantDAO> getAllManagers()
    {
        return this.managerRestaurantRepository.findAllManagers();
    }
}
