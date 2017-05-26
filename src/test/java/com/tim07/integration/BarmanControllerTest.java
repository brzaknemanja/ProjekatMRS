package com.tim07.integration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim07.domain.DTO.ManagerRestaurantDTO;
import com.tim07.domain.DTO.registrationDTO.EmployeeRegistrationDTO;
import com.tim07.domain.Entity.Barman;
import com.tim07.domain.Entity.ManagerRestaurant;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.repository.BarmanRepository;
import com.tim07.repository.ManagerRestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Katarina Cukurov on 24/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BarmanControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BarmanRepository barmanRepository;

    @Autowired
    private ManagerRestaurantRepository managerRestaurantRepository;

    private MockMvc mock;
    private ObjectMapper mapper;

    @Before
    public void setUp()
    {
        this.mock = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.mapper = new ObjectMapper();
        this.barmanRepository.save(new Barman("kaca", "cuki", UserType.Barman, "katarina", "cukurov", "21.12.2000.", 38,38));
        this.managerRestaurantRepository.save(new ManagerRestaurant("djudjakaca", "djuli", UserType.RestaurantManager, "djulinka", "cukurov", "djudja.cukurov@gmail.com"));
    }

    @Test
    public void registrationTest() throws Exception
    {
        EmployeeRegistrationDTO userIncorrect = new EmployeeRegistrationDTO("zika", "jovic", "zika", "lozinka", "lozinka2", "21.2.1991.", 42, 42);
        String userJsonIncorrect = mapper.writeValueAsString(userIncorrect);

        EmployeeRegistrationDTO userCorrect = new EmployeeRegistrationDTO("nemanja", "brzak", "brzi", "2222", "2222","10.10.1995.", 42, 38);
        String userJsonCorrect = mapper.writeValueAsString(userCorrect);

        mock.perform(post("/barman/registration", userIncorrect)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJsonIncorrect)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

        mock.perform(post("/barman/registration", userCorrect)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJsonCorrect)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
