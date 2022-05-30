//package br.com.lvm.restaurantapi.domain.repositoryImp;
//
//import br.com.lvm.restaurantapi.domain.model.State;
//import br.com.lvm.restaurantapi.domain.repository.StateRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Repository
//public class StateRepositoryImp implements StateRepository {
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Override
//    public List<State> findAllStates() {
//        return entityManager.createQuery("from State", State.class).getResultList();
//    }
//
//    @Override
//    public State searchStateById(Long id) {
//        return entityManager.find(State.class, id);
//    }
//
//    @Transactional
//    @Override
//    public State saveNewState(State state) {
//        return entityManager.merge(state);
//    }
//
//    @Transactional
//    @Override
//    public void deleteState(Long id) {
//        State state = searchStateById(id);
//
//        if (state == null){
//            throw new EmptyResultDataAccessException(1);
//        }
//
//        entityManager.remove(state);
//    }
//}
