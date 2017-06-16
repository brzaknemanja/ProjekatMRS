package com.tim07.domain;

import com.tim07.domain.Entity.RestaurantTable;
import com.tim07.domain.Enumeration.Segment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Katarina Cukurov on 24/04/2017.
 */
@RunWith(SpringRunner.class)
public class RestaurantTableTest {

    RestaurantTable restaurantTable;

    @Before
    public void SetUp()
    {
        restaurantTable = new RestaurantTable("",4, 15.6, 18.9, 45.6, Segment.GardenNoSmoking);
    }

    @Test
    public void testTableChairNumber(){ assertEquals((Integer)4, restaurantTable.getChairNumber());}

    @Test
    public  void testTableTop(){assertEquals((Double)15.6, restaurantTable.getTop());}

    @Test
    public void testTableLeft(){assertEquals((Double)18.9, restaurantTable.getLeft());}

    @Test
    public void testTableRotation(){assertEquals((Double)45.6, restaurantTable.getRotation());}

    @Test
    public void testTableRegion(){assertEquals(Segment.GardenNoSmoking, restaurantTable.getSegment());}
}
