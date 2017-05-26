package com.tim07.service;

import com.tim07.domain.Entity.ManagerRestaurant;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.repository.ManagerRestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerRestaurantRepositoryTest {

    @Autowired
    ManagerRestaurantRepository managerRestaurantRepository;

    @Before
    public void setUp()
    {
        this.managerRestaurantRepository.save(new ManagerRestaurant("djudja", "djuli", UserType.RestaurantManager, "djudja", "cukurov", "djudja.cukurov@gmail.com"));
        this.managerRestaurantRepository.save(new ManagerRestaurant("kacac", "cuki", UserType.RestaurantManager, "kaca", "cukurov", "kaca.cukurov@gmail.com"));
    }

    @Test
    public void findByUsernameTest()
    {
        ManagerRestaurant user1 = this.managerRestaurantRepository.findByUsername("djudja");
        assertEquals(new ManagerRestaurant("djudja", "djuli", UserType.RestaurantManager, "djudja", "cukurov", "djudja.cukurov@gmail.com"), user1);
    }
}
