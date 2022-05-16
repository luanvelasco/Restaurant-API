package br.com.lvm.restaurantapi.domain.serviceImp;

import br.com.lvm.restaurantapi.domain.exception.EntityInUseException;
import br.com.lvm.restaurantapi.domain.exception.EntityWasNotFoundException;
import br.com.lvm.restaurantapi.domain.model.Kitchen;
import br.com.lvm.restaurantapi.domain.repository.KitchenRepository;
import br.com.lvm.restaurantapi.domain.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class KitchenServiceImp implements KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Override
    public List<Kitchen> findAllKitchens() {
        return kitchenRepository.findAllKitchens();
    }

    @Override
    public Kitchen searchKitchenById(Long id){
            return kitchenRepository.searchKitchenById(id);
    }

    @Override
    public Kitchen saveNewKitchen(Kitchen kitchen) {
        return kitchenRepository.saveNewKitchen(kitchen);
    }

    @Override
    public void deleteKitchen(Long kitchenId) {
        try {

            kitchenRepository.deleteKitchen(kitchenId);

        }catch (EmptyResultDataAccessException e){ //Data access exception thrown when a result was expected to have at least one row (or element) but zero rows (or elements) were actually returned.
            throw new EntityWasNotFoundException(
                    String.format("The kitchen %d does not exist", kitchenId));

        }catch (DataIntegrityViolationException e){ //Happens when any kithen is being used used by a restaurant
            throw new EntityInUseException(
                    String.format("The kitchen %d cannot be removed as it is in use", kitchenId));
        }

    }
}
