package com.tim07.service;

import com.tim07.repository.BarmanRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BarmanRepositoryTest {

    @Autowired
    BarmanRepository barmanRepository;
}
