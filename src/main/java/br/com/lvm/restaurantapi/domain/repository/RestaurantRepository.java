package br.com.lvm.restaurantapi.domain.repository;

import br.com.lvm.restaurantapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {}
