package com.tim07.domain;

import com.tim07.domain.Entity.ManagerRestaurant;
import com.tim07.domain.Enumeration.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
@RunWith(SpringRunner.class)
public class ManagerRestaurantTest {

    ManagerRestaurant managerRestaurant;

    @Before
    public void setUp()
    {
        managerRestaurant = new ManagerRestaurant("djudja", "djuli", UserType.RestaurantManager, "djudja",
                "cukurov", "djudja@gmail.com");
    }

    @Test
    public void testManagerUsername(){ assertEquals("djudja", managerRestaurant.getUsername());}

    @Test
    public void testManagerPassword(){ assertEquals("djuli", managerRestaurant.getPassword());}

    @Test
    public void testManagerEmail(){ assertEquals("djudja@gmail.com", managerRestaurant.getEmail());}

    @Test
    public void testManagerName(){ assertEquals("djudja", managerRestaurant.getName());}

    @Test
    public void testManagerLastname(){ assertEquals("cukurov", managerRestaurant.getLastname());}

    @Test
    public void testManagerType() { assertEquals(UserType.RestaurantManager, managerRestaurant.getType());}
}
