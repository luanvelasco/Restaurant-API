package br.com.lvm.restaurantapi.domain.service;

import br.com.lvm.restaurantapi.domain.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> findAllRestaurants();
    Restaurant searchRestaurantById(Long id);
    Restaurant saveNewRestaurant(Restaurant restaurant);
    void deleteRestaurant(Long id);
}
