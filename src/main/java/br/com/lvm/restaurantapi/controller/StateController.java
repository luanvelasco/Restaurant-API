package br.com.lvm.restaurantapi.controller;

import br.com.lvm.restaurantapi.domain.exception.EntityInUseException;
import br.com.lvm.restaurantapi.domain.exception.EntityWasNotFoundException;
import br.com.lvm.restaurantapi.domain.model.State;
import br.com.lvm.restaurantapi.domain.service.StateService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> findAll(){
        return stateService.findAllStates();
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> findStateById(@PathVariable Long stateId){
        Optional<State> state = stateService.searchStateById(stateId);

        if (state.isPresent()){
            return ResponseEntity.ok(state.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<State> addNewState(@RequestBody State state){
            stateService.saveNewState(state);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(state);
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<State> updateState(
            @PathVariable Long stateId,
            @RequestBody State state){

        Optional<State> actualState = stateService.searchStateById(stateId);

        if (actualState.isPresent()){
            BeanUtils.copyProperties(state, actualState.get(), "id");

            State savedState = stateService.saveNewState(actualState.get());

            return ResponseEntity.ok(savedState);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<State> deleteState(@PathVariable Long stateId){
        try {

            stateService.deleteState(stateId);
            return ResponseEntity.noContent().build();

        }catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }catch (EntityWasNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
