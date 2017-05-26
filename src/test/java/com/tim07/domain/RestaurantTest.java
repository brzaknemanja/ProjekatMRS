package com.tim07.domain;

import com.tim07.domain.Entity.Restaurant;
import com.tim07.domain.Enumeration.KitchenType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
@RunWith(SpringRunner.class)
public class RestaurantTest {

    Restaurant restaurant;

    @Before
    public void setUp()
    {
        restaurant = new Restaurant("plava strela", "domaca kuhinja", "branka radicevica 74", "novi knezevac", KitchenType.Kineska);
    }

    @Test
    public void testNameRestaurant(){assertEquals("plava strela", restaurant.getName()); }

    @Test
    public void testDescriptionRestaurant(){ assertEquals("domaca kuhinja", restaurant.getDescription());}
}
