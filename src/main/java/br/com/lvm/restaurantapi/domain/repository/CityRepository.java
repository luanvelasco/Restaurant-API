package br.com.lvm.restaurantapi.domain.repository;

import br.com.lvm.restaurantapi.domain.model.City;

import java.util.List;

public interface CityRepository {
    List<City> findAllCities();
    City searchCityById(Long id);
    City saveNewCity(City city);
    void deleteCity(Long id);
}
