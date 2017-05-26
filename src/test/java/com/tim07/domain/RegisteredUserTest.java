package com.tim07.domain;

import static org.junit.Assert.assertEquals;

import com.tim07.domain.Entity.RegisteredUser;
import com.tim07.domain.Enumeration.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Ivana Zeljkovic on 10-Apr-17.
 */
@RunWith(SpringRunner.class)
public class RegisteredUserTest {

    RegisteredUser registeredUser;

    @Before
    public void setUp()
    {
        registeredUser = new RegisteredUser("korisnik", "lozinka", UserType.Registered, "Ivana", "Zeljkovic", "ivana.zeljkovic995@gmail.com");
    }

    @Test
    public void testUserUsername()
    {
        assertEquals("korisnik", registeredUser.getUsername());
    }

    @Test
    public void testUserPassword()
    {
        assertEquals("lozinka", registeredUser.getPassword());
    }

    @Test
    public void testUserType()
    {
        assertEquals(UserType.Registered, registeredUser.getType());
    }

    @Test
    public void testUserName()
    {
        assertEquals("Ivana", registeredUser.getName());
    }

    @Test
    public void testUserLastname()
    {
        assertEquals("Zeljkovic", registeredUser.getLastname());
    }

}
