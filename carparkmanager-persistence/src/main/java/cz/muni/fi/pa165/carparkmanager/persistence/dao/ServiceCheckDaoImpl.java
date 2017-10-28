package cz.muni.fi.pa165.carparkmanager.persistence.dao;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * @author Josef Marek
 */
@Repository
public class ServiceCheckDaoImpl implements ServiceCheckDao {
    @PersistenceContext
    private EntityManager em;

    public static String SELECT_ALL = "SELECT c FROM ServiceCheck c";

    @Override
    public void create(ServiceCheck c) {
        em.persist(c);
    }

    @Override
    public void delete(ServiceCheck c) {
        em.remove(em.contains(c) ? c : em.merge(c));
    }

    @Override
    public void update(ServiceCheck c) {
        em.merge(c);
    }

    @Override
    public ServiceCheck findById(Long id) {
        return em.find(ServiceCheck.class, id);
    }

    @Override
    public List<ServiceCheck> findAll() {
        return em.createQuery(SELECT_ALL, ServiceCheck.class).getResultList();
    }

}
