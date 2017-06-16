package com.tim07.controller;

import com.tim07.domain.DAO.RestaurantViewInListDAO;
import com.tim07.domain.DTO.*;
import com.tim07.domain.DTO.registrationDTO.UserOrManagerRegistrationDTO;
import com.tim07.domain.DTO.updateDTO.RestaurantRegistrationOrUpdateDTO;
import com.tim07.domain.Entity.*;
import com.tim07.domain.Autentification.JwtUser;
import com.tim07.domain.Enumeration.KitchenType;
import com.tim07.domain.Enumeration.Segment;
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

import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ManagerRestaurantService managerRestaurantService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ManagerSystemService managerSystemService;

    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(
            value = "/getRestaurant",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getRestaurant(@RequestHeader("Authorization") String userToken){
        JwtUser user = this.jwtService.getUser(userToken);
        ManagerRestaurant managerRestaurant = this.managerRestaurantService.getByUsername(user.getUsername());

        System.out.println(managerRestaurant);

        if(managerRestaurant != null){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            RestaurantRegistrationOrUpdateDTO restaurantRegistrationOrUpdateDTO = convertRestaurantToDTO(managerRestaurant.getRestaurant());

            return new ResponseEntity<RestaurantRegistrationOrUpdateDTO>(restaurantRegistrationOrUpdateDTO, headers, HttpStatus.OK);
        }
        return new ResponseEntity<ManagerRestaurant>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/getEmployeeRestaurant",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getEmployeeRestaurant(@RequestHeader("Authorization") String userToken){
        JwtUser user = this.jwtService.getUser(userToken);
        Employee employee = this.employeeService.findByUsername(user.getUsername());

        if(employee != null){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            RestaurantRegistrationOrUpdateDTO restaurantRegistrationOrUpdateDTO = null;

            switch (employee.getType()) {
                case Waiter:
                    restaurantRegistrationOrUpdateDTO = convertRestaurantToDTO(((Waiter) employee).getRestaurant());
                    break;
                case Barman:
                    restaurantRegistrationOrUpdateDTO = convertRestaurantToDTO(((Barman) employee).getRestaurant());
                    break;
                case Chef:
                    restaurantRegistrationOrUpdateDTO = convertRestaurantToDTO(((Chef) employee).getRestaurant());
                    break;
            }

            return new ResponseEntity<RestaurantRegistrationOrUpdateDTO>(restaurantRegistrationOrUpdateDTO, headers, HttpStatus.OK);
        }
        return new ResponseEntity<ManagerRestaurant>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/getAllRestaurants",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantViewInListDAO>> getAllRestaurants(@RequestHeader("Authorization") String userToken) {
        JwtUser user = this.jwtService.getUser(userToken);

        // proverava se da li je token iz zaglavlja zahteva odgovarajuci za nekog registrovanog korisnika ili sistemskog menadzera,
        // jer jedino ova dva tipa korisnika imaju privilegiju dobavljanje informacija o svim restoranima
        boolean currentUserIsRegisteredUser = true;
        boolean currentUserIsSystemManager = true;

        RegisteredUser currentRegisteredUser = this.registeredUserService.getByUsername(user.getUsername());
        if(currentRegisteredUser == null)
        {
            currentUserIsRegisteredUser = false;
            ManagerSystem currentManager = this.managerSystemService.getByUsername(user.getUsername());
            if(currentManager == null)
                currentUserIsSystemManager = false;
        }

        if(!currentUserIsRegisteredUser && !currentUserIsSystemManager)
            // token odgovara nekom drugom tipu korisnika koji nema privilegije za ovu operaciju
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else
        {
            List<RestaurantViewInListDAO> allRestaurants = this.restaurantService.getAllRestaurants();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            return new ResponseEntity<List<RestaurantViewInListDAO>>(allRestaurants, headers, HttpStatus.OK);
        }
    }

    @RequestMapping(
            value = "/updateName",
            method = RequestMethod.PUT,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantRegistrationOrUpdateDTO> updateName(@RequestHeader("Authorization") String userToken, @RequestBody String newName){
        JwtUser user = this.jwtService.getUser(userToken);

        ManagerRestaurant managerRestaurant = this.managerRestaurantService.getByUsername(user.getUsername());

        if(managerRestaurant != null) {
            managerRestaurant.getRestaurant().setName(newName);
            this.restaurantService.updateName(managerRestaurant.getRestaurant());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            RestaurantRegistrationOrUpdateDTO restaurantRegistrationOrUpdateDTO = convertRestaurantToDTO(managerRestaurant.getRestaurant());
            return new ResponseEntity<RestaurantRegistrationOrUpdateDTO>(restaurantRegistrationOrUpdateDTO, headers, HttpStatus.OK);
        }
        return new ResponseEntity<RestaurantRegistrationOrUpdateDTO>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/getTables",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDish(@RequestHeader("Authorization") String userToken){
        JwtUser user = this.jwtService.getUser(userToken);

        Employee employee = this.employeeService.findByUsername(user.getUsername());

        if(employee != null && employee.getType() == UserType.Waiter) {

            Restaurant restaurant = ((Waiter) employee).getRestaurant();
            List<TableDTO> tableDTOList = new ArrayList<>();
            List<RestaurantTable> tables = restaurant.getTables();
            for(int i = 0; i < tables.size(); i++){
                tableDTOList.add(convertTableToDTO(tables.get(i)));
            }


            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            return new ResponseEntity<List<TableDTO>>(tableDTOList, headers, HttpStatus.OK);
        }
        return new ResponseEntity<RestaurantRegistrationOrUpdateDTO>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/getDrinks",
            method = RequestMethod.PUT,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDrinks(@RequestHeader("Authorization") String userToken, @RequestBody String imeRestorana){

        JwtUser user = this.jwtService.getUser(userToken);
        Restaurant restaurant = this.restaurantService.getByName(imeRestorana);

        if(restaurant != null) {
            List<Drink> drinks = restaurant.getDrinks();
            List<FoodDTO> foodDTOS = new ArrayList<>();
            for(int i = 0; i < drinks.size(); i++){
                foodDTOS.add(convertDrinkToDTO(drinks.get(i)));
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            return new ResponseEntity<>(foodDTOS, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/getDishes",
            method = RequestMethod.PUT,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDish(@RequestHeader("Authorization") String userToken, @RequestBody String imeRestorana){
        JwtUser user = this.jwtService.getUser(userToken);

        Restaurant restaurant = this.restaurantService.getByName(imeRestorana);

        if(restaurant != null) {
            List<Dish> dishes = restaurant.getDishes();
            List<FoodDTO> foodDTOS = new ArrayList<>();
            for(int i = 0; i < dishes.size(); i++){
                foodDTOS.add(convertDishToDTO(dishes.get(i)));
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            return new ResponseEntity<List<FoodDTO>>(foodDTOS, headers, HttpStatus.OK);
        }
        return new ResponseEntity<RestaurantRegistrationOrUpdateDTO>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/updateStreet",
            method = RequestMethod.PUT,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantRegistrationOrUpdateDTO> updateStreet(@RequestHeader("Authorization") String userToken, @RequestBody String newStreet){
        JwtUser user = this.jwtService.getUser(userToken);

        ManagerRestaurant managerRestaurant = this.managerRestaurantService.getByUsername(user.getUsername());

        if(managerRestaurant != null) {
            managerRestaurant.getRestaurant().setStreet(newStreet);
            this.restaurantService.updateStreet(managerRestaurant.getRestaurant());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            RestaurantRegistrationOrUpdateDTO restaurantRegistrationOrUpdateDTO = convertRestaurantToDTO(managerRestaurant.getRestaurant());
            return new ResponseEntity<RestaurantRegistrationOrUpdateDTO>(restaurantRegistrationOrUpdateDTO, headers, HttpStatus.OK);
        }
        return new ResponseEntity<RestaurantRegistrationOrUpdateDTO>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/updateCity",
            method = RequestMethod.PUT,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantRegistrationOrUpdateDTO> updateCity(@RequestHeader("Authorization") String userToken, @RequestBody String newCity){
        JwtUser user = this.jwtService.getUser(userToken);

        ManagerRestaurant managerRestaurant = this.managerRestaurantService.getByUsername(user.getUsername());

        if(managerRestaurant != null) {
            managerRestaurant.getRestaurant().setCity(newCity);
            this.restaurantService.updateName(managerRestaurant.getRestaurant());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            RestaurantRegistrationOrUpdateDTO restaurantRegistrationOrUpdateDTO = convertRestaurantToDTO(managerRestaurant.getRestaurant());
            return new ResponseEntity<RestaurantRegistrationOrUpdateDTO>(restaurantRegistrationOrUpdateDTO, headers, HttpStatus.OK);
        }
        return new ResponseEntity<RestaurantRegistrationOrUpdateDTO>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/updateDescription",
            method = RequestMethod.PUT,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantRegistrationOrUpdateDTO> updateDescription(@RequestHeader("Authorization") String userToken, @RequestBody String newDescription){
        JwtUser user = this.jwtService.getUser(userToken);

        ManagerRestaurant managerRestaurant = this.managerRestaurantService.getByUsername(user.getUsername());

        if(managerRestaurant != null) {
            managerRestaurant.getRestaurant().setDescription(newDescription);
            this.restaurantService.updateDescription(managerRestaurant.getRestaurant());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            RestaurantRegistrationOrUpdateDTO restaurantRegistrationOrUpdateDTO = convertRestaurantToDTO(managerRestaurant.getRestaurant());
            return new ResponseEntity<RestaurantRegistrationOrUpdateDTO>(restaurantRegistrationOrUpdateDTO, headers, HttpStatus.OK);
        }
        return new ResponseEntity<RestaurantRegistrationOrUpdateDTO>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/getById",
            method = RequestMethod.PUT,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByID(@RequestHeader("Authorization") String userToken,
                                                                     @RequestBody String id){
        JwtUser user = this.jwtService.getUser(userToken);
        ManagerSystem managerSystem = this.managerSystemService.getByUsername(user.getUsername());
        if(managerSystem != null) {

            Restaurant restaurant = this.restaurantService.getById(Long.parseLong(id));
            RestaurantManagerSystemViewDTO restaurant1 = convertRestaurantToViewDTO(restaurant);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            return new ResponseEntity<RestaurantManagerSystemViewDTO>(restaurant1, headers, HttpStatus.OK);
        }
        return new ResponseEntity<RestaurantRegistrationOrUpdateDTO>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/registration",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registration(@RequestHeader("Authorization") String userToken,
                                       @RequestBody @Valid RestaurantRegistrationOrUpdateDTO restaurantRegistrationOrUpdateDTO, BindingResult result){

        JwtUser user = this.jwtService.getUser(userToken);
        ManagerSystem currentUser = this.managerSystemService.getByUsername(user.getUsername());

        if(currentUser != null){
            ManagerRestaurant managerRestaurant = this.managerRestaurantService.getByUsername(restaurantRegistrationOrUpdateDTO.getUsername());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            if(managerRestaurant != null){
                this.restaurantService.createNewRestaurant(restaurantRegistrationOrUpdateDTO.getName(), restaurantRegistrationOrUpdateDTO.getDescription(),
                        restaurantRegistrationOrUpdateDTO.getStreet(), restaurantRegistrationOrUpdateDTO.getCity(), restaurantRegistrationOrUpdateDTO.getKitchenType(),
                        managerRestaurant);

                return new ResponseEntity(headers, HttpStatus.OK);
            }
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/addTables",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity tableRegistration(@RequestHeader("Authorization") String userToken,
                                            @RequestBody @Valid TablesDTO table,
                                            BindingResult result){
        JwtUser user = this.jwtService.getUser(userToken);
        User currentUser = this.managerSystemService.getByUsername(user.getUsername());
        if(currentUser != null){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            if(result.hasErrors())
            {
                System.out.println(result.getAllErrors());
                return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
            }
            else
            {
                Restaurant restaurant = restaurantService.getByName(table.getRestaurant().getName());
                List<RestaurantTable> allTables = new ArrayList<>();
                Double top = 0.0;
                Double left = 0.0;
                Segment segment;
                for(int i = 0; i < table.getTables().size(); i++){
                    top = table.getTables().get(i).getTop();
                    left = table.getTables().get(i).getLeft();
                    if(left > 1000)
                        continue;
                    else if(left > 750) {
                        left -= 750;
                        if(top < 250)
                            segment = Segment.GardenSmoking;
                        else
                            segment = Segment.GardenNoSmoking;
                    }else if(left > 500) {
                        left -= 500;
                        if(top < 250)
                            segment = Segment.TerraceSmoking;
                        else
                            segment = Segment.TerraceNoSmoking;
                    }else if(left > 250) {
                        left -= 250;
                        if(top < 250)
                            segment = Segment.UpstairsSmoking;
                        else
                            segment = Segment.UpstairsNoSmoking;
                    }else{
                        if(top < 250)
                            segment = Segment.InsideSmoking;
                        else
                            segment = Segment.InsideNoSmoking;
                    }
                    if(top > 250)
                        top -=250;
                    RestaurantTable t = new RestaurantTable("",table.getTables().get(i).getChairNumber(), top, left,
                            table.getTables().get(i).getRotation(), segment);
                    t.setRestaurant(restaurant);
                    allTables.add(t);
                }
                boolean success = this.restaurantService.createNewTables(allTables);
                if(success)
                    return new ResponseEntity(headers, HttpStatus.OK);

                else
                    return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }


    @RequestMapping(
            value = "/addDrink",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addDrink(@RequestHeader("Authorization") String userToken,
                                       @RequestBody @Valid FoodDTO foodDTO, BindingResult result){

        JwtUser user = this.jwtService.getUser(userToken);
        ManagerRestaurant managerRestaurant = this.managerRestaurantService.getByUsername(user.getUsername());
        if(managerRestaurant != null){

            Drink drink = convertDTOToDrink(foodDTO);
            drink.setRestaurant(managerRestaurant.getRestaurant());
            boolean added = this.restaurantService.createNewDrink(drink);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            if(added){
                return new ResponseEntity(headers, HttpStatus.CREATED);
            }
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(
            value = "/addDish",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addDish(@RequestHeader("Authorization") String userToken,
                                   @RequestBody @Valid FoodDTO foodDTO, BindingResult result){

        JwtUser user = this.jwtService.getUser(userToken);
        ManagerRestaurant managerRestaurant = this.managerRestaurantService.getByUsername(user.getUsername());
        if(managerRestaurant != null){

            Dish dish = convertDTOToDish(foodDTO);
            dish.setRestaurant(managerRestaurant.getRestaurant());
            boolean added = this.restaurantService.createNewDish(dish);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);

            if(added){
                return new ResponseEntity(headers, HttpStatus.CREATED);
            }
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    public Restaurant getWorkCalendar(@PathVariable String name){

        return restaurantService.getByName(name);
    }

    @RequestMapping(
            value = "/getKitchenTypes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<KitchenType>> getKitchenTypes(@RequestHeader("Authorization") String userToken){
        JwtUser user = this.jwtService.getUser(userToken);
        ManagerSystem managerSystem = this.managerSystemService.getByUsername(user.getUsername());

        if(managerSystem != null){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            List<KitchenType> kitchenTypes = new ArrayList<>();
            for(int i = 0; i < KitchenType.values().length; i++)
                kitchenTypes.add(KitchenType.values()[i]);

            return new ResponseEntity<>(kitchenTypes, headers, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/addMoreManagers",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> addMoreManagers(@RequestHeader("Authorization") String userToken,
                                             @RequestBody @Valid RestaurantRegistrationOrUpdateDTO restaurant){
        JwtUser user = this.jwtService.getUser(userToken);
        ManagerSystem managerSystem = this.managerSystemService.getByUsername(user.getUsername());

        if(managerSystem != null){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", userToken);
            ManagerRestaurant managerRestaurant = this.managerRestaurantService.getByUsername(restaurant.getUsername());
            Restaurant restaurant1 = this.restaurantService.getByName(restaurant.getName());
            managerRestaurant.setRestaurant(restaurant1);
            restaurant1.getManagers().add(managerRestaurant);
            this.restaurantService.addNewManager(restaurant1);
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private RegisteredUser convertDTOToRegisteredUser(UserOrManagerRegistrationDTO userDto)
    {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(userDto, RegisteredUser.class);
    }

    private RestaurantRegistrationOrUpdateDTO convertRestaurantToDTO(Restaurant restaurant){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(restaurant, RestaurantRegistrationOrUpdateDTO.class);
    }

    private RestaurantManagerSystemViewDTO convertRestaurantToViewDTO(Restaurant restaurant){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(restaurant, RestaurantManagerSystemViewDTO.class);
    }

    private TableDTO convertTableToDTO(RestaurantTable table){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(table, TableDTO.class);
    }

    private FoodDTO convertDrinkToDTO(Drink drink){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(drink, FoodDTO.class);
    }

    private FoodDTO convertDishToDTO(Dish dish){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dish, FoodDTO.class);
    }

    private Drink convertDTOToDrink(FoodDTO foodDTO){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(foodDTO, Drink.class);
    }

    private Dish convertDTOToDish(FoodDTO foodDTO){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(foodDTO, Dish.class);
    }
}

