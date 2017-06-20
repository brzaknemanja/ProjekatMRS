package com.tim07.controller;

import com.tim07.domain.Autentification.JwtUser;
import com.tim07.domain.DTO.OrderItemDTO;
import com.tim07.domain.DTO.TableOrderDTO;
import com.tim07.domain.Entity.*;
import com.tim07.domain.Enumeration.ItemType;
import com.tim07.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brzak on 17.6.17..
 */
@RestController
@RequestMapping("/tableOrder")
public class TableOrderController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private TableOrderService tableOrderService;

    @Autowired
    private WaiterService waiterService;

    @Autowired
    private JwtService jwtService;

    @RequestMapping(
            value = "create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TableOrderDTO> createOrder(@RequestHeader("Authorization") String userToken,
                                      @RequestBody TableOrderDTO tableOrderDTO)
    {

        JwtUser user = this.jwtService.getUser(userToken);

        Waiter waiter = this.waiterService.getByUsername(user.getUsername());

        if (waiter != null){

            Restaurant restaurant = waiter.getRestaurant();

            TableOrder tableOrder = tableOrderService.createOrder(tableOrderDTO.getOrderItems(),restaurant, tableOrderDTO.getTableId());


            return new ResponseEntity<>(convertTableOrderToDTO(tableOrder),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "getWaiterOrders",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TableOrderDTO>> getWaiterOrders(@RequestHeader("Authorization") String userToken)
    {

        JwtUser user = this.jwtService.getUser(userToken);

        Waiter waiter = this.waiterService.getByUsername(user.getUsername());

        if (waiter != null){

            Restaurant restaurant = waiter.getRestaurant();
            List<TableOrderDTO> tableOrders = new ArrayList<>();

            for(TableOrder order : restaurant.getTableOrders()){
                for(TableSegment segment : waiter.getTableSegments()){
                    if(segment.getSegment() == order.getRestaurantTable().getSegment()){
                        tableOrders.add(convertTableOrderToDTO(order));
                    }
                }
            }

            return new ResponseEntity<>(tableOrders,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private TableOrderDTO convertTableOrderToDTO(TableOrder order){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(order, TableOrderDTO.class);
    }

}
