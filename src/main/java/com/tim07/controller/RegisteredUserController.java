package com.tim07.controller;

import com.tim07.domain.DAO.RegisteredUserSearchDAO;
import com.tim07.domain.DTO.registrationDTO.UserOrManagerRegistrationDTO;
import com.tim07.domain.Autentification.JwtUser;
import com.tim07.domain.Entity.RegisteredUser;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.service.JwtService;
import com.tim07.service.RegisteredUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ivana Zeljkovic on 09-Apr-17.
 */
@RestController
@RequestMapping(value = "/registeredUser")
public class RegisteredUserController {

    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired
    private JwtService jwtService;

    @RequestMapping(
            value = "/registration",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guestRegistration(@RequestBody @Valid UserOrManagerRegistrationDTO newUser, BindingResult result) {
        if(result.hasErrors()){
            System.out.println(result.getAllErrors());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            if(!newUser.getPassword().equals(newUser.getPassword2()))
                return new ResponseEntity(HttpStatus.BAD_REQUEST);

            RegisteredUser newRegisteredUser = convertDTOToRegisteredUser(newUser);
            newRegisteredUser.setType(UserType.Registered);
            boolean userCreated = this.registeredUserService.createNewUser(newRegisteredUser);
            if(!userCreated)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity(HttpStatus.CREATED);
        }
    }

    @RequestMapping(
            value = "/searchUsers/{parameter}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RegisteredUserSearchDAO>> searchUsers(@RequestHeader("Authorization") String userToken, @PathVariable String parameter) {
        JwtUser user = this.jwtService.getUser(userToken);

        RegisteredUser currentUser = this.registeredUserService.getByUsername(user.getUsername());

        System.out.println(parameter);
        if(currentUser != null){
            List<RegisteredUserSearchDAO> foundUsers = this.registeredUserService.findUsers(currentUser.getUsername(), parameter);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            return new ResponseEntity<>(foundUsers, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/addFriend",
            method = RequestMethod.PUT,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredUser> addFriend(@RequestHeader("Authorization") String userToken, @RequestBody String friendUsername){
        JwtUser user = this.jwtService.getUser(userToken);

        RegisteredUser currentUser = this.registeredUserService.getByUsername(user.getUsername());

        if(currentUser != null){
            RegisteredUser friend = this.registeredUserService.getByUsername(friendUsername);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            if(friend != null){
                this.registeredUserService.addFriend(currentUser, friend);

                return new ResponseEntity<>(currentUser, headers, HttpStatus.OK);
            }
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/acceptRequest",
            method = RequestMethod.PUT,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredUser> acceptFriendRequest(@RequestHeader("Authorization") String userToken, @RequestBody String friendUsername){
        JwtUser user = this.jwtService.getUser(userToken);

        RegisteredUser currentUser = this.registeredUserService.getByUsername(user.getUsername());

        if(currentUser != null){
            RegisteredUser friend = this.registeredUserService.getByUsername(friendUsername);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            if(friend != null){
                boolean success = this.registeredUserService.acceptFriendRequest(currentUser, friend);

                if(success)
                    return new ResponseEntity<>(currentUser, headers, HttpStatus.OK);
                return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/deleteFriend",
            method = RequestMethod.PUT,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredUser> deleteFriend(@RequestHeader("Authorization") String userToken, @RequestBody String friendUsername){
        JwtUser user = this.jwtService.getUser(userToken);

        RegisteredUser currentUser = this.registeredUserService.getByUsername(user.getUsername());

        if(currentUser != null){
            RegisteredUser friend = this.registeredUserService.getByUsername(friendUsername);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            if(friend != null){
                this.registeredUserService.removeFriend(currentUser, friend);

                return new ResponseEntity<>(currentUser, headers, HttpStatus.OK);
            }
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/deleteRequest",
            method = RequestMethod.PUT,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredUser> deleteFriendRequest(@RequestHeader("Authorization") String userToken, @RequestBody String friendUsername){
        JwtUser user = this.jwtService.getUser(userToken);

        RegisteredUser currentUser = this.registeredUserService.getByUsername(user.getUsername());

        if(currentUser != null){
            RegisteredUser friend = this.registeredUserService.getByUsername(friendUsername);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            if(friend != null){
                boolean success = this.registeredUserService.deleteFriendRequest(currentUser, friend);
                if(success)
                    return new ResponseEntity<>(currentUser, headers, HttpStatus.OK);
                return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private RegisteredUser convertDTOToRegisteredUser(UserOrManagerRegistrationDTO userDto)
    {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(userDto, RegisteredUser.class);
    }
}
