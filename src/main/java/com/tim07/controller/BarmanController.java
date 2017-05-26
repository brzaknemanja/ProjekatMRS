package com.tim07.controller;

import com.tim07.domain.DTO.registrationDTO.EmployeeRegistrationDTO;
import com.tim07.domain.Entity.Barman;
import com.tim07.domain.Entity.ManagerRestaurant;
import com.tim07.domain.Entity.User;
import com.tim07.domain.Autentification.JwtUser;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.service.BarmanService;
import com.tim07.service.JwtService;
import com.tim07.service.ManagerRestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
@RestController
@RequestMapping("/barman")
public class BarmanController {

    @Autowired
    private ManagerRestaurantService managerRestaurantService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BarmanService barmanService;


    @RequestMapping(
            value = "/registration",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity barmanRegistration(@RequestBody @Valid EmployeeRegistrationDTO newUser,
                                                   @RequestHeader("Authorization") String userToken)
    {
        JwtUser user = this.jwtService.getUser(userToken);
        ManagerRestaurant managerRestaurant = this.managerRestaurantService.getByUsername(user.getUsername());
        if(managerRestaurant != null)
        {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            if(!newUser.getPassword().equals(newUser.getPassword2()))
                return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
            Barman barman = convertDTOToBarman(newUser);
            barman.setType(UserType.Barman);
            barman.setRestaurant(managerRestaurant.getRestaurant());
            boolean successful = this.barmanService.createNewBarman(barman);
            if(successful)
                return new ResponseEntity(headers, HttpStatus.CREATED);
            else
                return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }


    private Barman convertDTOToBarman(EmployeeRegistrationDTO userDto)
    {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(userDto, Barman.class);
    }
}
