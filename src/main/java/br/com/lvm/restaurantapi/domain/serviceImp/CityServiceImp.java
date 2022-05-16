package br.com.lvm.restaurantapi.domain.serviceImp;

import br.com.lvm.restaurantapi.domain.exception.EntityInUseException;
import br.com.lvm.restaurantapi.domain.exception.EntityWasNotFoundException;
import br.com.lvm.restaurantapi.domain.model.City;
import br.com.lvm.restaurantapi.domain.model.State;
import br.com.lvm.restaurantapi.domain.repository.CityRepository;
import br.com.lvm.restaurantapi.domain.repository.StateRepository;
import br.com.lvm.restaurantapi.domain.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImp implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Override
    public List<City> findAllCities() {
        return cityRepository.findAllCities();
    }

    @Override
    public City searchCityById(Long id) {
        return cityRepository.searchCityById(id);
    }

    @Override
    public City saveNewCity(City city) {
        Long stateId = city.getState().getId();
        State state = stateRepository.searchStateById(stateId);

        if (state == null){
            throw new EntityWasNotFoundException(
                    String.format("There wasn't found any state with code %d", stateId));
        }

        city.setState(state);
        return cityRepository.saveNewCity(city);
    }

    @Override
    public void deleteCity(Long id) {
        try {

            cityRepository.deleteCity(id);

        }catch (EmptyResultDataAccessException e){ //Data access exception thrown when a result was expected to have at least one row (or element) but zero rows (or elements) were actually returned.
            throw new EntityWasNotFoundException(
                    String.format("The city %d does not exist", id));

        }catch (DataIntegrityViolationException e){ //Happens when any kithen is being used used by a restaurant
            throw new EntityInUseException(
                    String.format("The city %d cannot be removed as it is in use", id));
        }

    }
}
