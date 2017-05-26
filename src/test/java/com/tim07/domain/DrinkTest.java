package com.tim07.domain;

import com.tim07.domain.Entity.Drink;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Katarina Cukurov on 30/04/2017.
 */
@RunWith(SpringRunner.class)
public class DrinkTest {

    Drink drink;

    @Before
    public void setUp(){
        drink = new Drink("tekila", "opis pica", 200.45);
    }

    @Test
    public void testDrinkName(){assertEquals("tekila", drink.getName());}

    @Test
    public void testDrinkDescription(){assertEquals("opis pica", drink.getDescription());}

    @Test
    public void testDrinkPrice(){assertEquals((Double)200.45, drink.getPrice());}
}
