package com.tim07.controller;

import com.tim07.domain.DTO.registrationDTO.EmployeeRegistrationDTO;
import com.tim07.domain.Entity.ManagerRestaurant;
import com.tim07.domain.Entity.User;
import com.tim07.domain.Autentification.JwtUser;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.domain.Entity.Waiter;
import com.tim07.service.JwtService;
import com.tim07.service.ManagerRestaurantService;
import com.tim07.service.UserService;
import com.tim07.service.WaiterService;
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
 * Created by freez on 10-Apr-17.
 */
@RestController
@RequestMapping(value = "/waiter")
public class WaiterController
{
    @Autowired
    private ManagerRestaurantService managerRestaurantService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private WaiterService waiterService;

    @RequestMapping(
            value = "/registration",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity waiterRegistration(@RequestHeader("Authorization") String userToken,
                                                     @RequestBody @Valid EmployeeRegistrationDTO newUser, BindingResult result)
    {
        JwtUser user = this.jwtService.getUser(userToken);
        ManagerRestaurant managerRestaurant = this.managerRestaurantService.getByUsername(user.getUsername());
        if(managerRestaurant != null)
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
                    return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);;
                Waiter waiter = convertDTOToWaiter(newUser);
                waiter.setType(UserType.Waiter);
                waiter.setRestaurant(managerRestaurant.getRestaurant());
                boolean successful = this.waiterService.createNewUser(waiter);
                if(!successful)
                    return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
                else
                    return new ResponseEntity(headers, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    private Waiter convertDTOToWaiter(EmployeeRegistrationDTO userDto)
    {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(userDto, Waiter.class);
    }
}
