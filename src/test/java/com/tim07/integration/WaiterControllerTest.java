package com.tim07.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim07.domain.DTO.registrationDTO.EmployeeRegistrationDTO;
import com.tim07.domain.Entity.ManagerRestaurant;
import com.tim07.domain.Entity.Waiter;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.repository.ManagerRestaurantRepository;
import com.tim07.repository.WaiterRepository;
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
 * Created by Katarina Cukurov on 25/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WaiterControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private ManagerRestaurantRepository managerRestaurantRepository;

    private MockMvc mock;
    private ObjectMapper mapper;

    @Before
    public void setUp()
    {
        this.mock = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.mapper = new ObjectMapper();
        this.waiterRepository.save(new Waiter("kaca", "cuki", UserType.Barman, "katarina", "cukurov", "21.12.2000.", 38,38));
        this.managerRestaurantRepository.save(new ManagerRestaurant("djudjakaca", "djuli", UserType.RestaurantManager, "djulinka", "cukurov", "djudja.cukurov@gmail.com"));
    }

    @Test
    public void registrationTest() throws Exception
    {
        EmployeeRegistrationDTO userIncorrect = new EmployeeRegistrationDTO("zika", "jovic", "zika", "lozinka", "lozinka", "21.2.1991.", 42, 42);
        String userJsonIncorrect = mapper.writeValueAsString(userIncorrect);

        EmployeeRegistrationDTO userCorrect = new EmployeeRegistrationDTO("zika", "jovic", "jova", "lozinka", "lozinka", "21.2.1991.", 42, 42);
        String userJsonCorrect = mapper.writeValueAsString(userCorrect);

        mock.perform(post("/waiter/registration", userIncorrect)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJsonIncorrect)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

        mock.perform(post("/waiter/registration", userCorrect)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJsonCorrect)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
