package br.com.lvm.restaurantapi.domain.repository;

import br.com.lvm.restaurantapi.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface KitchenRepository{
    List<Kitchen> findAllKitchens();
    Kitchen searchKitchenById(Long id);
    Kitchen saveNewKitchen(Kitchen kitchen);
    void deleteKitchen(Long id);
}
