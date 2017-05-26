package com.tim07.domain;

import com.tim07.domain.Entity.Barman;
import com.tim07.domain.Enumeration.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */

public class BarmanTest {

    Barman barman;

    @Before
    public void setUp()
    {
        barman = new Barman("korisnik", "spori", UserType.Barman, "Nemanja", "Brzak", "1.1.2000.",38,38);
    }

    @Test
    public void testBarmanUsername()
    {
        assertEquals("korisnik", barman.getUsername());
    }

    @Test
    public void testBarmanPassword()
    {
        assertEquals("spori", barman.getPassword());
    }

    @Test
    public void testBarmanType()
    {
        assertEquals(UserType.Barman, barman.getType());
    }

    @Test
    public void testBarmanName()
    {
        assertEquals("Nemanja", barman.getName());
    }

    @Test
    public void testBarmanLastname()
    {
        assertEquals("Brzak", barman.getLastname());
    }

    @Test
    public void testBarmanDateOfBirt(){assertEquals("1.1.2000.", barman.getDateOfBirth());}

    @Test
    public void testBarmanDressSize(){assertEquals((Integer) 38, barman.getDressSize());}

    @Test
    public void testBarmanShoeSize(){assertEquals((Integer) 38, barman.getShoeSize());}
}
