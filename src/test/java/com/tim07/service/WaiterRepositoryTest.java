package com.tim07.service;


import com.tim07.domain.Enumeration.UserType;
import com.tim07.domain.Entity.Waiter;
import com.tim07.repository.WaiterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by freez on 11-Apr-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WaiterRepositoryTest
{
    @Autowired
    private WaiterRepository waiterRepository;

    @Before
    public void setUp()
    {
        this.waiterRepository.save(new Waiter("marija", "loz111", UserType.Waiter, "Marija", "Belic",  "1.1.",38,38));
        this.waiterRepository.save(new Waiter("stefan", "loz2222", UserType.Waiter, "Stefan", "Stajic",  "1.1.",38,38));
        this.waiterRepository.save(new Waiter("jovana", "loz3333", UserType.Waiter, "Jovana", "Drinic",   "1.1.",38,38));
        this.waiterRepository.save(new Waiter("filip", "loz4444", UserType.Waiter, "Filip", "Kostic",   "1.1.",38,38));
    }

    @Test
    public void findByUsernameTest()
    {
        Waiter user1 = this.waiterRepository.findByUsername("marija");
        assertEquals(new Waiter("marija", "loz111", UserType.Waiter, "Marija", "Belic",   "1.1.",38,38), user1);
    }

    @Test
    public void updateTest()
    {
        this.waiterRepository.save(new Waiter("marija", "loz111", UserType.Waiter, "novoIme", "Belic",  "1.1.",38,38));
        Waiter user = this.waiterRepository.findByUsername("marija");
        assertEquals("novoIme", user.getName());
    }
}
