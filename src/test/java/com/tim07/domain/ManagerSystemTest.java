package com.tim07.domain;

import static org.junit.Assert.assertEquals;

import com.tim07.domain.Entity.ManagerSystem;
import com.tim07.domain.Enumeration.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Katarina Cukurov on 11/04/2017.
 */

@RunWith(SpringRunner.class)
public class ManagerSystemTest {

    ManagerSystem managerSystem;

    @Before
    public void setUp()
    {
        managerSystem = new ManagerSystem("nikola", "gara", UserType.Manager, "Nikola",
                            "Garabandic", "kantagara@gmail.com");
    }

    @Test
    public void testManagerUsername(){ assertEquals("nikola", managerSystem.getUsername());}

    @Test
    public void testManagerPassword(){ assertEquals("gara", managerSystem.getPassword());}

    @Test
    public void testManagerEmail(){ assertEquals("kantagara@gmail.com", managerSystem.getEmail());}

    @Test
    public void testManagerName(){ assertEquals("Nikola", managerSystem.getName());}

    @Test
    public void testManagerLastname(){ assertEquals("Garabandic", managerSystem.getLastname());}

    @Test
    public void testManagerType() { assertEquals(UserType.Manager, managerSystem.getType());}
}
