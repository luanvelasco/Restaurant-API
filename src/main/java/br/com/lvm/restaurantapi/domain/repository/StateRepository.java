package br.com.lvm.restaurantapi.domain.repository;

import br.com.lvm.restaurantapi.domain.model.City;
import br.com.lvm.restaurantapi.domain.model.State;

import java.util.List;

public interface StateRepository {
    List<State> findAllStates();
    State searchStateById(Long id);
    State saveNewState(State state);
    void deleteState(Long id);
}
