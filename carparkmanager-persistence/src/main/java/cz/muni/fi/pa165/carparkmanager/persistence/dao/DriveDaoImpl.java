package cz.muni.fi.pa165.carparkmanager.persistence.dao;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
/**
 * Implementations of Dao interface for the Drive entity
 * 
 * @author Jakub Juřena
 */
public class DriveDaoImpl implements DriveDao {

    @PersistenceContext
    private EntityManager em;
    
    public static final String SELECT_ALL = "SELECT drive FROM Drive drive";
    
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
        return em.createNamedQuery(SELECT_ALL, Drive.class).getResultList();
    }
}
