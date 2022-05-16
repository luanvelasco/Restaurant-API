package br.com.lvm.restaurantapi.domain.serviceImp;

import br.com.lvm.restaurantapi.domain.exception.EntityInUseException;
import br.com.lvm.restaurantapi.domain.exception.EntityWasNotFoundException;
import br.com.lvm.restaurantapi.domain.model.Kitchen;
import br.com.lvm.restaurantapi.domain.model.Restaurant;
import br.com.lvm.restaurantapi.domain.repository.KitchenRepository;
import br.com.lvm.restaurantapi.domain.repository.RestaurantRepository;
import br.com.lvm.restaurantapi.domain.service.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    @Override
    public List<Restaurant> findAllRestaurants() {
        return restaurantRepository.findAllRestaurants();
    }

    @Override
    public Restaurant searchRestaurantById(Long id) {
        return restaurantRepository.searchRestaurantById(id);
    }

    @Override
    public Restaurant saveNewRestaurant(Restaurant restaurant) {
        Long cozinhaId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.searchKitchenById(cozinhaId);

        if (kitchen == null){
            throw new EntityWasNotFoundException(
                    String.format("There is no kitchen registration with code %d", cozinhaId));
        }

        restaurant.setKitchen(kitchen);
        return restaurantRepository.saveNewRestaurant(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {
        try {

            restaurantRepository.deleteRestaurant(id);

        }catch (EmptyResultDataAccessException e){ //Data access exception thrown when a result was expected to have at least one row (or element) but zero rows (or elements) were actually returned.
            throw new EntityWasNotFoundException(
                    String.format("The Restaurant %d does not exist", id));

        }catch (DataIntegrityViolationException e){ //Happens when any kithen is being used used by a restaurant
            throw new EntityInUseException(
                    String.format("The Restaurant %d cannot be removed as it is in use", id));
        }

    }
}
