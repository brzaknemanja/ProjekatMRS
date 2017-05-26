package com.tim07.service;

import com.tim07.repository.DishRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Katarina Cukurov on 30/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DishRepositoryTest {

    @Autowired
    DishRepository dishRepository;
}
