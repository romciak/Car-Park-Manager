package cz.muni.fi.pa165.carparkmanager.persistence.dao;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementations of CRUD operations for the Car entity.
 *
 * @author Roman Nedelka
 */
@Transactional
@Repository
@NamedQuery(
        name = "findAllCars",
        query = "SELECT c FROM Car c"
)
public class CarDaoImpl implements CarDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Car c) {
        em.persist(c);
    }

    @Override
    public void delete(Car c) {
        em.remove(em.contains(c) ? c : em.merge(c));
    }

    @Override
    public void update(Car c) {
        em.merge(c);
    }

    @Override
    public Car findById(Long id) {
        return em.find(Car.class, id);
    }

    @Override
    public List<Car> findAll() {
        return em.createNamedQuery("findAllCars", Car.class).getResultList();
    }

}
