package com.tim07.service;

import com.tim07.domain.Entity.ManagerSystem;
import com.tim07.domain.Entity.RegisteredUser;
import com.tim07.domain.Entity.User;
import com.tim07.domain.Entity.Waiter;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.repository.ManagerSystemRepository;
import com.tim07.repository.RegisteredUserRepository;
import com.tim07.repository.UserRepository;
import com.tim07.repository.WaiterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ivana Zeljkovic on 11-Apr-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ManagerSystemRepository managerRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private WaiterRepository waiterRepository;

    @Before
    public void setUp()
    {
       this.registeredUserRepository.save(new RegisteredUser("stefan", "loz2222", UserType.Registered, "Stefan", "Stajic", "stefan@gmail.com"));
        this.managerRepository.save(new ManagerSystem("jovana", "loz3333", UserType.Registered, "Jovana", "Drinic", "jovana@gmail.com"));
        this.waiterRepository.save(new Waiter("filip", "loz4444", UserType.Registered, "Filip", "Kostic", "1.1.",38,38));
    }

    @Test
    public void findByUsernameTest()
    {
        User user1 = this.userRepository.findByUsername("stefan");
        User user2 = this.userRepository.findByUsername("jovana");
        User user3 = this.userRepository.findByUsername("filip");
        assertEquals(new RegisteredUser("stefan", "loz2222", UserType.Registered, "Stefan", "Stajic", "stefan@gmail.com"), (RegisteredUser)user1);
        assertEquals(new Waiter("filip", "loz4444", UserType.Registered, "Filip", "Kostic", "1.1.", 38, 38), (Waiter) user3);
        assertEquals(new ManagerSystem("jovana", "loz3333", UserType.Registered, "Jovana", "Drinic", "jovana@gmail.com"), (ManagerSystem)user2);
    }
}
