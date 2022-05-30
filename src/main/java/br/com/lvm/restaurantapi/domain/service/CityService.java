package br.com.lvm.restaurantapi.domain.service;

import br.com.lvm.restaurantapi.domain.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City> findAllCities();
    Optional<City> searchCityById(Long id);
    City saveNewCity(City city);
    void deleteCity(Long id);
}
