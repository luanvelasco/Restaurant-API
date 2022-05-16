package br.com.lvm.restaurantapi.domain.service;

import br.com.lvm.restaurantapi.domain.model.City;

import java.util.List;

public interface CityService {
    List<City> findAllCities();
    City searchCityById(Long id);
    City saveNewCity(City city);
    void deleteCity(Long id);
}
