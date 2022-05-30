//package br.com.lvm.restaurantapi.domain.repositoryImp;
//
//import br.com.lvm.restaurantapi.domain.model.Kitchen;
//import br.com.lvm.restaurantapi.domain.repository.KitchenRepository;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Repository
//public class KitchenRepositoryImp implements KitchenRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//
//    @Override
//    public List<Kitchen> findAllKitchens() {
//        return entityManager.createQuery("from Kitchen", Kitchen.class).getResultList();
//    }
//
//    @Override
//    public Kitchen searchKitchenById(Long id) {
//        return entityManager.find(Kitchen.class, id);
//    }
//
//    @Transactional
//    @Override
//    public Kitchen saveNewKitchen(Kitchen kitchen) {
//
//        return entityManager.merge(kitchen);
//    }
//
//    @Transactional
//    @Override
//    public void deleteKitchen(Long kitchenId) {
//        Kitchen kitchen = searchKitchenById(kitchenId);
//
//        if (kitchen == null){
//            throw new EmptyResultDataAccessException(1);
//        }
//
//        entityManager.remove(kitchen);
//    }
//}
