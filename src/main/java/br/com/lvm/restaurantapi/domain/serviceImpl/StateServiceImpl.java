package br.com.lvm.restaurantapi.domain.serviceImpl;

import br.com.lvm.restaurantapi.domain.exception.EntityInUseException;
import br.com.lvm.restaurantapi.domain.exception.EntityWasNotFoundException;
import br.com.lvm.restaurantapi.domain.model.State;
import br.com.lvm.restaurantapi.domain.repository.StateRepository;
import br.com.lvm.restaurantapi.domain.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Override
    public List<State> findAllStates() {
        return stateRepository.findAll();
    }

    @Override
    public Optional<State> searchStateById(Long id) {
        return stateRepository.findById(id);
    }

    @Override
    public State saveNewState(State state) {
        return stateRepository.save(state);
    }

    @Override
    public void deleteState(Long id) {
        try {

            stateRepository.deleteById(id);

        }catch (EmptyResultDataAccessException e){ //Data access exception thrown when a result was expected to have at least one row (or element) but zero rows (or elements) were actually returned.
            throw new EntityWasNotFoundException(
                    String.format("The State %d does not exist", id));

        }catch (DataIntegrityViolationException e){ //Happens when any kithen is being used used by a restaurant
            throw new EntityInUseException(
                    String.format("The State %d cannot be removed as it is in use", id));
        }

    }
}
