package br.com.lvm.restaurantapi.domain.service;

import br.com.lvm.restaurantapi.domain.model.Kitchen;

import java.util.List;
import java.util.Optional;

public interface KitchenService {
    List<Kitchen> findAllKitchens();
    Optional<Kitchen> searchKitchenById(Long id);
    Kitchen saveNewKitchen(Kitchen kitchen);
    void deleteKitchen(Long Id);
}
