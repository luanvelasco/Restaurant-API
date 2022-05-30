package br.com.lvm.restaurantapi.domain.service;

import br.com.lvm.restaurantapi.domain.model.State;

import java.util.List;
import java.util.Optional;

public interface StateService {
    List<State> findAllStates();
    Optional<State> searchStateById(Long id);
    State saveNewState(State state);
    void deleteState(Long id);
}
