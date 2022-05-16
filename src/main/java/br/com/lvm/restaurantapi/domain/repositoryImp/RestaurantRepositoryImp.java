package br.com.lvm.restaurantapi.domain.repositoryImp;

import br.com.lvm.restaurantapi.domain.model.Restaurant;
import br.com.lvm.restaurantapi.domain.repository.RestaurantRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RestaurantRepositoryImp implements RestaurantRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurant> findAllRestaurants() {
        return entityManager.createQuery("from Restaurant", Restaurant.class).getResultList();
    }

    @Override
    public Restaurant searchRestaurantById(Long id) {
        return entityManager.find(Restaurant.class, id);
    }

    @Transactional
    @Override
    public Restaurant saveNewRestaurant(Restaurant restaurant) {
        return entityManager.merge(restaurant);
    }

    @Transactional
    @Override
    public void deleteRestaurant(Long id) {
        Restaurant restaurant = searchRestaurantById(id);

        if (restaurant == null){
            throw  new EmptyResultDataAccessException(1);
        }

        entityManager.remove(restaurant);
    }
}
