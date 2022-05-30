package br.com.lvm.restaurantapi.controller;

import br.com.lvm.restaurantapi.domain.exception.EntityInUseException;
import br.com.lvm.restaurantapi.domain.exception.EntityWasNotFoundException;
import br.com.lvm.restaurantapi.domain.model.Kitchen;
import br.com.lvm.restaurantapi.domain.model.Restaurant;
import br.com.lvm.restaurantapi.domain.service.KitchenService;
import br.com.lvm.restaurantapi.domain.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        Optional<Restaurant> restaurant = restaurantService.searchRestaurantById(restaurantId);

        if (restaurant.isPresent()){
            return ResponseEntity.ok(restaurant.get());
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
    public ResponseEntity<?> updateRestaurant(
            @PathVariable Long restaurantId,
            @RequestBody Restaurant restaurant){

        try {

            Restaurant actualRestaurant = restaurantService.searchRestaurantById(restaurantId).orElse(null);

            if (actualRestaurant != null ){
                BeanUtils.copyProperties(restaurant, actualRestaurant, "id", "paymentType", "address");

                Restaurant savedRestaurant = restaurantService.saveNewRestaurant(actualRestaurant);

                return ResponseEntity.ok(savedRestaurant);
            }

            return ResponseEntity.notFound().build();

        }catch (EntityWasNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> parcialUpdateRestaurant(
            @PathVariable Long restaurantId,
            @RequestBody Map<String, Object> fields){

        Restaurant restaurantToBeUpdate = restaurantService.searchRestaurantById(restaurantId)
                .orElse(null);

        if (restaurantToBeUpdate == null){
            return ResponseEntity.notFound().build();
        }

        merge(fields, restaurantToBeUpdate);

        return updateRestaurant(restaurantId, restaurantToBeUpdate);

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


    /*
     * The function of this method is to merge the updated values
     * of (fields) into the object (restaurantToBeUpdate)
     *
     * Let's use Reflections: is the ability for us to inspect java objects at runtime and even
     *  change these objects through methods or by calling attributes.
     */
    private void merge(Map<String, Object> fields, Restaurant restaurantToBeUpdate) {

        //Let's use ObjectMapper to serialize the json to object To resolve conversion issues
        ObjectMapper objectMapper = new ObjectMapper();

        Restaurant restaurantMappedFields = objectMapper.convertValue(fields, Restaurant.class);

        fields.forEach((propertyName, propertyValue) -> {

            Field field = ReflectionUtils.findField(Restaurant.class, propertyName);

            //to make the variable accessible
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, restaurantMappedFields);

            System.out.println(propertyName + " = " + propertyValue + " = " + newValue);

            ReflectionUtils.setField(field, restaurantToBeUpdate, newValue);
        });
    }
}
