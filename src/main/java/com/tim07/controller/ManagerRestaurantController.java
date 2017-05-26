package com.tim07.controller;

import com.tim07.domain.DAO.ManagerRestaurantDAO;
import com.tim07.domain.DTO.ManagerRestaurantDTO;
import com.tim07.domain.DTO.registrationDTO.UserOrManagerRegistrationDTO;
import com.tim07.domain.Entity.ManagerRestaurant;
import com.tim07.domain.Entity.ManagerSystem;
import com.tim07.domain.Entity.User;
import com.tim07.domain.Autentification.JwtUser;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */

@RestController
@RequestMapping(value = "/managerRestaurant")
public class ManagerRestaurantController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ManagerRestaurantService managerRestaurantService;

    @Autowired
    private ManagerSystemService managerSystemService;

    @RequestMapping(
            value = "/registration",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity managerRestaurantRegistration(@RequestHeader("Authorization") String userToken,
                                                                           @RequestBody @Valid UserOrManagerRegistrationDTO newUser,
                                                                           BindingResult result)
    {
        JwtUser user = this.jwtService.getUser(userToken);
        User currentUser = this.managerSystemService.getByUsername(user.getUsername());
        if(currentUser != null)
        {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            if(result.hasErrors())
            {
                System.out.println(result.getAllErrors());
                return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
            }
            else
            {
                if(!newUser.getPassword().equals(newUser.getPassword2()))
                    return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
                ManagerRestaurant managerRestaurant = convertDTOToManagerRestaurant(newUser);
                managerRestaurant.setType(UserType.RestaurantManager);
                boolean successful = this.managerRestaurantService.createNewManagerRestaurant(managerRestaurant);

                if(!successful)
                    return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
                else
                    return new ResponseEntity(headers, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ManagerRestaurantDAO>> getAllManagers(@RequestHeader("Authorization") String userToken) {
        JwtUser user = this.jwtService.getUser(userToken);
        ManagerSystem currentUser = this.managerSystemService.getByUsername(user.getUsername());
        if(currentUser != null){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            List<ManagerRestaurantDAO> allManagers = this.managerRestaurantService.getAllManagers();

            return new ResponseEntity<>(allManagers, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private ManagerRestaurant convertDTOToManagerRestaurant(UserOrManagerRegistrationDTO userDto)
    {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(userDto, ManagerRestaurant.class);
    }
}
