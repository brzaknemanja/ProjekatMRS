package com.tim07.domain;

import com.tim07.domain.Entity.Dish;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Katarina Cukurov on 30/04/2017.
 */
@RunWith(SpringRunner.class)
public class DishTest {

    Dish dish;

    @Before
    public void setUp(){
        dish = new Dish("sarma", "jelo sa kupusom", 650.05);
    }

    @Test
    public void testDishName(){assertEquals("sarma", dish.getName());}

    @Test
    public void testDishDescription(){assertEquals("jelo sa kupusom", dish.getDescription());}

    @Test
    public void testDishPrice(){assertEquals((Double)650.05, dish.getPrice());}
}
