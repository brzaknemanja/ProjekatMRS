package com.tim07.service;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import com.tim07.domain.DAO.RegisteredUserSearchDAO;
import com.tim07.domain.DTO.FriendDTO;
import com.tim07.domain.Entity.RegisteredUser;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.repository.RegisteredUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivana Zeljkovic on 10-Apr-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisteredUserRepositoryTest {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Before
    public void setUp()
    {
        this.registeredUserRepository.save(new RegisteredUser("marija", "loz111", UserType.Registered, "Marija", "Belic", "marija@gmail.com"));
        this.registeredUserRepository.save(new RegisteredUser("stefan", "loz2222", UserType.Registered, "Stefan", "Stajic", "stefan@gmail.com"));
        this.registeredUserRepository.save(new RegisteredUser("jovana", "loz3333", UserType.Registered, "Jovana", "Drinic", "jovana@gmail.com"));
        this.registeredUserRepository.save(new RegisteredUser("filip", "loz4444", UserType.Registered, "Filip", "Kostic", "filip@gmail.com"));
    }

    @Test
    public void findByUsernameTest()
    {
        RegisteredUser user1 = this.registeredUserRepository.findByUsername("marija");
        assertEquals(new RegisteredUser("marija", "loz111", UserType.Registered, "Marija", "Belic", "marija@gmail.com"), user1);
    }

    @Test
    public void updateTest()
    {
        this.registeredUserRepository.save(new RegisteredUser("marija", "loz111", UserType.Registered, "novoIme", "Belic", "marija@gmail.com"));
        RegisteredUser user = this.registeredUserRepository.findByUsername("marija");
        assertEquals("novoIme", user.getName());
    }

    @Test
    public void findUsersTest()
    {
        List<FriendDTO> users = new ArrayList<FriendDTO>();
        users.add(new FriendDTO("Marija", "Belic", "marija", null));
        String currentUserUsername = "filip";
        List<RegisteredUserSearchDAO> result = this.registeredUserRepository.findUsers(currentUserUsername, "marija");
        assertEquals(users, result);
    }

}
