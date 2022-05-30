//package br.com.lvm.restaurantapi.domain.repositoryImp;
//
//import br.com.lvm.restaurantapi.domain.model.City;
//import br.com.lvm.restaurantapi.domain.repository.CityRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Repository
//public class CityRepositoryImp implements CityRepository {
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Override
//    public List<City> findAllCities() {
//        return entityManager.createQuery("from City", City.class).getResultList();
//    }
//
//    @Override
//    public City searchCityById(Long id) {
//        return entityManager.find(City.class, id);
//    }
//
//    @Transactional
//    @Override
//    public City saveNewCity(City city) {
//        return entityManager.merge(city);
//    }
//
//    @Transactional
//    @Override
//    public void deleteCity(Long id) {
//        City city = searchCityById(id);
//
//        if (city == null){
//            throw new EmptyResultDataAccessException(1);
//        }
//
//        entityManager.remove(city);
//    }
//}
