package com.tim07.controller;

import com.tim07.domain.Autentification.JwtUser;
import com.tim07.domain.DTO.*;
import com.tim07.domain.DTO.loginDTO.EmployeeFirstLoginDTO;
import com.tim07.domain.DTO.loginDTO.UserLoginDTO;
import com.tim07.domain.DTO.updateDTO.UpdatePasswordDTO;
import com.tim07.domain.Entity.*;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Ivana Zeljkovic on 08-Apr-17.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired
    private WaiterService waiterService;

    @Autowired
    private ChefService chefService;

    @Autowired
    private BarmanService barmanService;

    @RequestMapping(
            value = "/getCurrentUser",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserOnSession(@RequestHeader("Authorization") String userToken) {
        JwtUser user = this.jwtService.getUser(userToken);

        User currentUser = this.userService.findByUsername(user.getUsername());

        if(currentUser != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);


            switch (currentUser.getType()){
                case Registered:
                    return new ResponseEntity<RegisteredUserDTO>(convertRegisteredUserToDTO((RegisteredUser) currentUser), headers, HttpStatus.OK);
                case RestaurantManager:
                    return new ResponseEntity<ManagerRestaurantDTO>(convertManagerRestaurantToDTO((ManagerRestaurant) currentUser), headers, HttpStatus.OK);
                case Manager:
                    return new ResponseEntity<ManagerSystemDTO>(convertManagerSystemToDTO((ManagerSystem) currentUser), headers, HttpStatus.OK);
                case Barman:
                    return new ResponseEntity<BarmanDTO>(convertBarmanToDTO((Barman) currentUser), headers, HttpStatus.OK);
                case Waiter:
                    return new ResponseEntity<WaiterDTO>(convertWaiterToDTO((Waiter) currentUser), headers, HttpStatus.OK);
                case Chef:
                    return new ResponseEntity<ChefDTO>(convertChefToDTO((Chef) currentUser), headers, HttpStatus.OK);
            }
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody UserLoginDTO userLoginDTO){
        Boolean userExist = this.userService.authenticate(userLoginDTO.getUsername(), userLoginDTO.getPassword());

        if(userExist){
            HttpHeaders headers = new HttpHeaders();

            JwtUser jwtUser = new JwtUser(userLoginDTO.getUsername());
            headers.add("Authorization", this.jwtService.getToken(jwtUser));

            return new ResponseEntity(headers, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }


    @RequestMapping(
            value = "/logout",
            method = RequestMethod.GET)
    public ResponseEntity logout(@RequestHeader("Authorization") String userToken){
        JwtUser user = this.jwtService.getUser(userToken);

        User currentUser = this.userService.findByUsername(user.getUsername());

        if(currentUser != null)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/updateName",
            method = RequestMethod.PUT,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateName(@RequestHeader("Authorization") String userToken, @RequestBody String newName){
        JwtUser user = this.jwtService.getUser(userToken);

        User currentUser = this.userService.findByUsername(user.getUsername());

        if(currentUser != null) {
            currentUser.setName(newName);

            if(currentUser.getType() == UserType.Registered)
               this.registeredUserService.updateName((RegisteredUser) currentUser);
            else if(currentUser.getType() == UserType.Waiter)
                this.waiterService.updateName((Waiter) currentUser);
            else if(currentUser.getType() == UserType.Chef)
                this.chefService.updateName((Chef) currentUser);
            else if(currentUser.getType() == UserType.Barman)
                this.barmanService.updateName((Barman) currentUser);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            return new ResponseEntity<User>(currentUser, headers, HttpStatus.NO_CONTENT); // status ok?
        }
        return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/updateLastname",
            method = RequestMethod.PUT,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateLastname(@RequestHeader("Authorization") String userToken, @RequestBody String newLastname){
        JwtUser user = this.jwtService.getUser(userToken);

        User currentUser = this.userService.findByUsername(user.getUsername());

        if(currentUser != null) {
            currentUser.setLastname(newLastname);

            if(currentUser.getType() == UserType.Registered)
                this.registeredUserService.updateLastname((RegisteredUser) currentUser);
            else if(currentUser.getType() == UserType.Waiter)
                this.waiterService.updateLastname((Waiter) currentUser);
            else if(currentUser.getType() == UserType.Chef)
                this.chefService.updateLastname((Chef) currentUser);
            else if(currentUser.getType() == UserType.Barman)
                this.barmanService.updateLastname((Barman) currentUser);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            return new ResponseEntity<User>(currentUser, headers, HttpStatus.NO_CONTENT); // status ok?
        }
        return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/updatePassword",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updatePassword(@RequestHeader("Authorization") String userToken, @RequestBody UpdatePasswordDTO passwordObject){
        JwtUser user = this.jwtService.getUser(userToken);

        User currentUser = this.userService.findByUsername(user.getUsername());

        if(currentUser != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            if(currentUser.getPassword().equals(passwordObject.getOldPassword())) {
                currentUser.setPassword(passwordObject.getNewPassword());

                if(currentUser.getType() == UserType.Registered)
                    this.registeredUserService.updatePassword((RegisteredUser) currentUser);
                else if(currentUser.getType() == UserType.Waiter)
                    this.waiterService.updatePassword((Waiter) currentUser);
                else if(currentUser.getType() == UserType.Chef)
                    this.chefService.updatePassword((Chef) currentUser);
                else if(currentUser.getType() == UserType.Barman)
                    this.barmanService.updatePassword((Barman) currentUser);

                return new ResponseEntity<User>(currentUser, headers, HttpStatus.NO_CONTENT); // status ok?
            }
            else
                return new ResponseEntity<User>(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
    }

    private RegisteredUserDTO convertRegisteredUserToDTO(RegisteredUser user){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, RegisteredUserDTO.class);
    }

    private ManagerSystemDTO convertManagerSystemToDTO(ManagerSystem manager){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(manager, ManagerSystemDTO.class);
    }

    private ManagerRestaurantDTO convertManagerRestaurantToDTO(ManagerRestaurant manager){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(manager, ManagerRestaurantDTO.class);
    }

    private BarmanDTO convertBarmanToDTO(Barman barman){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(barman, BarmanDTO.class);
    }

    private WaiterDTO convertWaiterToDTO(Waiter waiter){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(waiter, WaiterDTO.class);
    }

    private ChefDTO convertChefToDTO(Chef chef){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(chef, ChefDTO.class);
    }

    private boolean IsFirstLoginEmployee(String username) {

        User user = userService.findByUsername(username);

        if(!(user instanceof Employee))return false;

        Employee employee = (Employee) user;
        return employee.isFirstLogin();
    }

}
