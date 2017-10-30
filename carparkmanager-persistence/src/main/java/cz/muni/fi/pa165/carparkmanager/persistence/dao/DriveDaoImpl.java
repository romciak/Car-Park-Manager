package cz.muni.fi.pa165.carparkmanager.persistence.dao;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementations of Dao interface for the Drive entity
 * 
 * @author Jakub Juřena
 */
@Transactional
@Repository
public class DriveDaoImpl implements DriveDao {

    @PersistenceContext
    private EntityManager em;
    
    public static String SELECT_ALL = "SELECT d FROM Drive d";
    
    @Override
    public void create(Drive drive) {
        em.persist(drive);
    }

    @Override
    public void delete(Drive drive) {
        em.remove(em.contains(drive) ? drive : em.merge(drive));
    }

    @Override
    public void update(Drive drive) {
        em.merge(drive);
    }

    @Override
    public Drive findById(Long id) {
        return em.find(Drive.class, id);
    }

    @Override
    public List<Drive> findAll() {
        return em.createQuery(SELECT_ALL, Drive.class).getResultList();
    }
}
