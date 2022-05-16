package br.com.lvm.restaurantapi.controller;

import br.com.lvm.restaurantapi.domain.exception.EntityInUseException;
import br.com.lvm.restaurantapi.domain.exception.EntityWasNotFoundException;
import br.com.lvm.restaurantapi.domain.model.City;
import br.com.lvm.restaurantapi.domain.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Entity;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> findAllCities(){
        return cityService.findAllCities();
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> findCityById(@PathVariable Long cityId){
        City city = cityService.searchCityById(cityId);

        if (city != null){
            return ResponseEntity.ok(city);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<?> addNewCity(@RequestBody City city){
        try {
            city =  cityService.saveNewCity(city);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(city);
        } catch (EntityWasNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }


    }

    @PutMapping("/{cityId}")
    public ResponseEntity<City> updateCity(
            @PathVariable Long cityId,
            @RequestBody City city){

        City actualCity = cityService.searchCityById(cityId);

        if (actualCity != null){
            BeanUtils.copyProperties(city, actualCity, "id");

            cityService.saveNewCity(actualCity);

            return ResponseEntity.ok(actualCity);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @DeleteMapping("/{cityId}")
    public ResponseEntity<City> deleteCity(@PathVariable Long cityId){
        try {

            cityService.deleteCity(cityId);
            return ResponseEntity.noContent().build();

        }catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }catch (EntityWasNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
