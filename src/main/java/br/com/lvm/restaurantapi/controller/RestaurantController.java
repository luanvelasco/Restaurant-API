package br.com.lvm.restaurantapi.controller;

import br.com.lvm.restaurantapi.domain.exception.EntityInUseException;
import br.com.lvm.restaurantapi.domain.exception.EntityWasNotFoundException;
import br.com.lvm.restaurantapi.domain.model.Kitchen;
import br.com.lvm.restaurantapi.domain.model.Restaurant;
import br.com.lvm.restaurantapi.domain.service.KitchenService;
import br.com.lvm.restaurantapi.domain.service.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    public List<Restaurant> findAllRestaurants(){
        return restaurantService.findAllRestaurants();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> findRestaurantById(@PathVariable Long restaurantId){

        Restaurant restaurant = restaurantService.searchRestaurantById(restaurantId);

        if (restaurant != null){
            return ResponseEntity.ok(restaurant);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> addNewRestaurant(@RequestBody Restaurant restaurant){
        try {
            restaurant = restaurantService.saveNewRestaurant(restaurant);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurant);

        }catch (EntityWasNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @PathVariable Long restaurantId,
            @RequestBody Restaurant restaurant){

        Restaurant actualRestaurant = restaurantService.searchRestaurantById(restaurantId);

        if (actualRestaurant != null){
            BeanUtils.copyProperties(restaurant, actualRestaurant, "id");

            restaurantService.saveNewRestaurant(actualRestaurant);

            return ResponseEntity.ok(actualRestaurant);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{restaurantId}")
    public  ResponseEntity<Kitchen> deleteRestaurant(@PathVariable Long restaurantId){
        try {
            restaurantService.deleteRestaurant(restaurantId);
            return ResponseEntity.noContent().build();

        }catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }catch (EntityWasNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
