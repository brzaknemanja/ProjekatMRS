package com.tim07.domain;

import com.tim07.domain.Entity.Chef;
import com.tim07.domain.Enumeration.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
@RunWith(SpringRunner.class)
public class ChefTest {

    Chef chef;

    @Before
    public void setUp()
    {
        chef = new Chef("korisnik", "srednji", UserType.Chef, "Nemanja", "Brzak", "1.1.2000.",38,38);
    }

    @Test
    public void testChefUsername()
    {
        assertEquals("korisnik", chef.getUsername());
    }

    @Test
    public void testChefPassword()
    {
        assertEquals("srednji", chef.getPassword());
    }

    @Test
    public void testChefType()
    {
        assertEquals(UserType.Chef, chef.getType());
    }

    @Test
    public void testChefName()
    {
        assertEquals("Nemanja", chef.getName());
    }

    @Test
    public void testChefLastname()
    {
        assertEquals("Brzak", chef.getLastname());
    }

    @Test
    public void testChefDateOfBirt(){assertEquals("1.1.2000.", chef.getDateOfBirth());}

    @Test
    public void testChefDressSize(){assertEquals((Integer) 38, chef.getDressSize());}

    @Test
    public void testChefShoeSize(){assertEquals((Integer) 38, chef.getShoeSize());}
}
