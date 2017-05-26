package com.tim07.domain;

import com.tim07.domain.Entity.Waiter;
import com.tim07.domain.Enumeration.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by freez on 11-Apr-17.
 */
@RunWith(SpringRunner.class)
public class WaiterTest
{
    Waiter waiter;

    @Before
    public void setUp()
    {
        waiter = new Waiter("korisnik", "lozinka", UserType.Waiter, "Nemanja", "Brzak", "1.1.2000.",38,38);
    }

    @Test
    public void testUserUsername()
    {
        assertEquals("korisnik", waiter.getUsername());
    }

    @Test
    public void testWaiterPassword()
    {
        assertEquals("lozinka", waiter.getPassword());
    }

    @Test
    public void testWaiterType()
    {
        assertEquals(UserType.Waiter, waiter.getType());
    }

    @Test
    public void testWaiterName()
    {
        assertEquals("Nemanja", waiter.getName());
    }

    @Test
    public void testWaiterLastname()
    {
        assertEquals("Brzak", waiter.getLastname());
    }

    @Test
    public void testWaiterDateOfBirt(){assertEquals("1.1.2000.", waiter.getDateOfBirth());}

    @Test
    public void testWaiterDressSize(){assertEquals((Integer) 38, waiter.getDressSize());}

    @Test
    public void testWaiterShoeSize(){assertEquals((Integer) 38, waiter.getShoeSize());}
}
