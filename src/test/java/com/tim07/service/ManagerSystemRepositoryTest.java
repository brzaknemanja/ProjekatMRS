package com.tim07.service;

import com.tim07.domain.Entity.ManagerSystem;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.repository.ManagerSystemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Katarina Cukurov on 11/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerSystemRepositoryTest {

    @Autowired
    private ManagerSystemRepository managerSystemRepository;

    @Before
    public void setUp()
    {
        this.managerSystemRepository.save(new ManagerSystem("djudja", "djuli", UserType.RestaurantManager, "djudja", "cukurov", "djudja.cukurov@gmail.com"));
        this.managerSystemRepository.save(new ManagerSystem("kacac", "cuki", UserType.RestaurantManager, "kaca", "cukurov", "kaca.cukurov@gmail.com"));
    }

    @Test
    public void findByUsernameTest()
    {
        ManagerSystem user1 = this.managerSystemRepository.findByUsername("djudja");
        assertEquals(new ManagerSystem("djudja", "djuli", UserType.RestaurantManager, "djudja", "cukurov", "djudja.cukurov@gmail.com"), user1);
    }
}
