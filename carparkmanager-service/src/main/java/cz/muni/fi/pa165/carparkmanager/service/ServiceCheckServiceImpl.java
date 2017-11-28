package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.ServiceCheckDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Juřena
 */
@Service
public class ServiceCheckServiceImpl implements ServiceCheckService {

    @Autowired
    private ServiceCheckDao serviceCheckDao;

    @Override
    public void create(ServiceCheck serviceCheck) {
        try {
            serviceCheckDao.create(serviceCheck);
        } catch (Exception e) {
            throw new DataAccessException("Cannot create ServiceCheck: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void delete(ServiceCheck serviceCheck) {
        try {
            serviceCheckDao.delete(serviceCheck);
        } catch (Exception e) {
            throw new DataAccessException("Cannot delete ServiceCheck: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void update(ServiceCheck serviceCheck) {
        try {
            serviceCheckDao.update(serviceCheck);
        } catch (Exception e) {
            throw new DataAccessException("Cannot update ServiceCheck: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public ServiceCheck findById(Long id) {
        try {
            return serviceCheckDao.findById(id);
        } catch (Exception e) {
            throw new DataAccessException("Cannot find ServiceCheck: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<ServiceCheck> findAll() {
        try {
            return serviceCheckDao.findAll();
        } catch (Exception e) {
            throw new DataAccessException("Cannot findAll ServiceCheck: " + e.getMessage(), e) {
            };
        }
    }

}
