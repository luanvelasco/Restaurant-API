package br.com.lvm.restaurantapi.controller;

import br.com.lvm.restaurantapi.domain.exception.EntityInUseException;
import br.com.lvm.restaurantapi.domain.exception.EntityWasNotFoundException;
import br.com.lvm.restaurantapi.domain.model.Kitchen;
import br.com.lvm.restaurantapi.domain.repository.KitchenRepository;
import br.com.lvm.restaurantapi.domain.service.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kithen")
public class KitchenController {

    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    public List<Kitchen> findAll(){
        return kitchenService.findAllKitchens();
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> findKithenById(@PathVariable Long kitchenId){
        Optional<Kitchen> kitchen = kitchenService.searchKitchenById(kitchenId);

        if (kitchen.isPresent()){
            return ResponseEntity.ok(kitchen.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> addNewKitchen(@RequestBody Kitchen kitchen){
        kitchenService.saveNewKitchen(kitchen);

        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> upDatedKitchen(
            @PathVariable Long kitchenId,
            @RequestBody Kitchen kitchen){

        Optional<Kitchen> actualKitchen = kitchenService.searchKitchenById(kitchenId);

        if (actualKitchen.isPresent()) {
            BeanUtils.copyProperties(kitchen, actualKitchen.get(), "id");

            Kitchen savedKitchen = kitchenService.saveNewKitchen(actualKitchen.get());
            return ResponseEntity.ok(savedKitchen);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> deleteKitchen(@PathVariable Long kitchenId){
        try {
            kitchenService.deleteKitchen(kitchenId);
            return ResponseEntity.noContent().build();

        }catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }catch (EntityWasNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }
}
