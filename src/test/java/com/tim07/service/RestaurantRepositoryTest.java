package com.tim07.service;

import com.tim07.domain.Entity.Restaurant;
import com.tim07.domain.Enumeration.KitchenType;
import com.tim07.repository.RestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Katarina Cukurov on 18/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Before
    public void setUp()
    {
        this.restaurantRepository.save(new Restaurant("plava strela", "domaca kuhinja", "branka radicevica", "novi knezevac", KitchenType.Kineska));
        this.restaurantRepository.save(new Restaurant("co ceng", "kineska hrana", "srpska", "novi knezevac", KitchenType.Kineska));
    }

    @Test
    public void findByNameTest()
    {
        Restaurant restaurant = this.restaurantRepository.findByName("plava strela");
        assertEquals(new Restaurant("plava strela", "domaca kuhinja","branka radicevica", "novi knezevac", KitchenType.Kineska), restaurant);
    }
}
