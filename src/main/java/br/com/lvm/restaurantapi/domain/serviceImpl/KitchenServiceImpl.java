package br.com.lvm.restaurantapi.domain.serviceImpl;

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
import java.util.Optional;

@Service
public class KitchenServiceImpl implements KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Override
    public List<Kitchen> findAllKitchens() {
        return kitchenRepository.findAll();
    }

    @Override
    public Optional<Kitchen> searchKitchenById(Long id){
            return kitchenRepository.findById(id);
    }

    @Override
    public Kitchen saveNewKitchen(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Override
    public void deleteKitchen(Long kitchenId) {
        try {

            kitchenRepository.deleteById(kitchenId);

        }catch (EmptyResultDataAccessException e){ //Data access exception thrown when a result was expected to have at least one row (or element) but zero rows (or elements) were actually returned.
            throw new EntityWasNotFoundException(
                    String.format("The kitchen %d does not exist", kitchenId));

        }catch (DataIntegrityViolationException e){ //Happens when any kithen is being used used by a restaurant
            throw new EntityInUseException(
                    String.format("The kitchen %d cannot be removed as it is in use", kitchenId));
        }

    }
}
