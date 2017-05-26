package com.tim07.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim07.domain.DTO.registrationDTO.UserOrManagerRegistrationDTO;
import com.tim07.domain.Entity.ManagerSystem;
import com.tim07.domain.Entity.RegisteredUser;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.repository.ManagerSystemRepository;
import com.tim07.repository.RegisteredUserRepository;
import com.tim07.service.RegisteredUserService;
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
 * Created by Ivana Zeljkovic on 19-Apr-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisteredUserControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired
    private ManagerSystemRepository managerSystemRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    private MockMvc mock;
    private ObjectMapper mapper;

    @Before
    public void setUp()
    {
        this.mock = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.mapper = new ObjectMapper();
        this.registeredUserRepository.save(new RegisteredUser("mika", "mikic", UserType.Registered, "mika", "mikic", "mika@gmail.com"));
        this.managerSystemRepository.save(new ManagerSystem("zika", "zikic", UserType.Manager, "zika", "zikic", "zika@gmail.com"));
    }

    @Test
    public void registrationTest() throws Exception {
        UserOrManagerRegistrationDTO userIncorrect = new UserOrManagerRegistrationDTO("zika", "jovic", "zika", "lozinka", "lozinka", "jovic@gmail.com");
        String userJsonIncorrect = mapper.writeValueAsString(userIncorrect);

        UserOrManagerRegistrationDTO userCorrect = new UserOrManagerRegistrationDTO("jova", "jovic", "jova", "lozinka", "lozinka", "jova@gmail.com");
        String userJsonCorrect = mapper.writeValueAsString(userCorrect);

        mock.perform(post("/registeredUser/registration", userIncorrect)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userJsonIncorrect)
                    .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

        mock.perform(post("/registeredUser/registration", userCorrect)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userJsonCorrect)
                    .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }
}
