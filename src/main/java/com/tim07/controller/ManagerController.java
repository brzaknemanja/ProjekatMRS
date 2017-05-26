package com.tim07.controller;

import com.tim07.domain.DTO.registrationDTO.UserOrManagerRegistrationDTO;
import com.tim07.domain.Entity.ManagerSystem;
import com.tim07.domain.Entity.User;
import com.tim07.domain.Autentification.JwtUser;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.service.JwtService;
import com.tim07.service.ManagerSystemService;
import com.tim07.service.UserService;
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
 * Created by Katarina Cukurov on 09/04/2017.
 */
@RestController
@RequestMapping(value = "/managerSystem")
public class ManagerController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ManagerSystemService managerSystemService;

    @RequestMapping(
            value = "/registrationManager",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity managerRegistration(@RequestHeader("Authorization") String userToken,
                                                             @RequestBody @Valid UserOrManagerRegistrationDTO newUser,
                                                             BindingResult result)
    {
        JwtUser user = this.jwtService.getUser(userToken);
        User current = this.managerSystemService.getByUsername(user.getUsername());
        if(current != null)
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
                ManagerSystem managerSystem = convertDTOToManagerSystem(newUser);
                managerSystem.setType(UserType.Manager);
                boolean successful = this.managerSystemService.createNewManager(managerSystem);
                if(!successful)
                    return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
                else
                    return new ResponseEntity(headers, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    private ManagerSystem convertDTOToManagerSystem(UserOrManagerRegistrationDTO userDto)
    {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(userDto, ManagerSystem.class);
    }
}
