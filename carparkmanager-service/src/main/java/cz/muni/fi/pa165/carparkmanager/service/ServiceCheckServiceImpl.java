package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.ServiceCheckDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Juřena
 */
@Service
public class ServiceCheckServiceImpl implements ServiceCheckService {
    
    @Autowired
    private ServiceCheckDao ServiceCheckDao;

    @Override
    public void create(ServiceCheck serviceCheck) {
        ServiceCheckDao.create(serviceCheck);
    }

    @Override
    public void delete(ServiceCheck serviceCheck) {
        ServiceCheckDao.delete(serviceCheck);
    }

    @Override
    public void update(ServiceCheck serviceCheck) {
        ServiceCheckDao.update(serviceCheck);
    }

    @Override
    public ServiceCheck findById(Long id) {
        return ServiceCheckDao.findById(id);
    }

    @Override
    public List<ServiceCheck> findAll() {
        return ServiceCheckDao.findAll();
    }
    
}
