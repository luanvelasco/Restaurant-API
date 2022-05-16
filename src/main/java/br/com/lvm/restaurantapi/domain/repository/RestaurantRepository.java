package br.com.lvm.restaurantapi.domain.repository;

import br.com.lvm.restaurantapi.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> findAllRestaurants();
    Restaurant searchRestaurantById(Long id);
    Restaurant saveNewRestaurant(Restaurant restaurant);
    void deleteRestaurant(Long id);
}
