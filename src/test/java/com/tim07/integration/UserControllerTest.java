package com.tim07.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim07.domain.Autentification.JwtUser;
import com.tim07.domain.DTO.loginDTO.UserLoginDTO;
import com.tim07.domain.Entity.RegisteredUser;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.repository.RegisteredUserRepository;
import com.tim07.service.JwtService;
import com.tim07.service.UserService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Ivana Zeljkovic on 18-Apr-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    private ObjectMapper mapper;
    private MockMvc mock;

    @Before
    public void setUp(){
        this.mock = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.mapper = new ObjectMapper();
        this.registeredUserRepository.save(new RegisteredUser("mika", "mikic", UserType.Registered, "mika", "mikic", "mika@gmail.com"));
    }

    @Test
    public void loginTest() throws Exception {
        UserLoginDTO userIncorrect = new UserLoginDTO("pera", "peric");
        String userJsonIncorrect = mapper.writeValueAsString(userIncorrect);

        UserLoginDTO userCorrect = new UserLoginDTO("mika", "mikic");
        String userJsonCorrect = mapper.writeValueAsString(userCorrect);
        JwtUser userJwt = new JwtUser(userCorrect.getUsername());
        String jwtToken = this.jwtService.getToken(userJwt);

        mock.perform(post("/user/login", userIncorrect)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJsonIncorrect)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());

        mock.perform(post("/user/login", userCorrect)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJsonCorrect)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

