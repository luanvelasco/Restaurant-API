package br.com.lvm.restaurantapi.domain.service;

import br.com.lvm.restaurantapi.domain.model.State;

import java.util.List;

public interface StateService {
    List<State> findAllStates();
    State searchStateById(Long id);
    State saveNewState(State state);
    void deleteState(Long id);
}
