package br.com.lvm.restaurantapi.domain.service;

import br.com.lvm.restaurantapi.domain.model.Kitchen;
import org.springframework.stereotype.Service;

import java.util.List;

public interface KitchenService {
    List<Kitchen> findAllKitchens();
    Kitchen searchKitchenById(Long id);
    Kitchen saveNewKitchen(Kitchen kitchen);
    void deleteKitchen(Long Id);
}
