package com.tim07.service;

import com.tim07.domain.Entity.RestaurantTable;
import com.tim07.domain.Enumeration.Segment;
import com.tim07.repository.TableRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Katarina Cukurov on 24/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TableRepositoryTest {

    @Autowired
    private TableRepository tableRepository;

    @Before
    public void setUp()
    {
        this.tableRepository.save(new RestaurantTable("",4,18.6,45.2,85.6, Segment.GardenNoSmoking));
        this.tableRepository.save(new RestaurantTable("",6,85.6,56.2,120.5,Segment.InsideNoSmoking));
    }
}
