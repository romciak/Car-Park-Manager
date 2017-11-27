package cz.muni.fi.pa165.carparkmanager.service.facade;

import cz.muni.fi.pa165.carparkmanager.api.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.ServiceCheckFacade;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import cz.muni.fi.pa165.carparkmanager.service.ServiceCheckService;
import cz.muni.fi.pa165.carparkmanager.service.utils.DataMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jakub Juřena
 */
@Service
@Transactional
public class ServiceCheckFacadeImpl implements ServiceCheckFacade{

    @Autowired
    private ServiceCheckService serviceCheckService;
    
    @Autowired
    private DataMapper mapper;
    
    @Override
    public void create(ServiceCheckDTO s) {
        ServiceCheck serviceCheck = mapper.mapTo(s, ServiceCheck.class);
        serviceCheckService.create(serviceCheck);
    }

    @Override
    public void delete(ServiceCheckDTO s) {
        ServiceCheck serviceCheck = mapper.mapTo(s, ServiceCheck.class);
        serviceCheckService.delete(serviceCheck);}

    @Override
    public void update(ServiceCheckDTO s) {
        ServiceCheck serviceCheck = mapper.mapTo(s, ServiceCheck.class);
        serviceCheckService.update(serviceCheck);}

    @Override
    public ServiceCheckDTO findById(Long id) {
        ServiceCheck serviceCheck = serviceCheckService.findById(id);
        return mapper.mapTo(serviceCheck, ServiceCheckDTO.class);
    }

    @Override
    public List<ServiceCheckDTO> findAll() {
        List<ServiceCheck> serviceChecks = serviceCheckService.findAll();
        return mapper.mapTo(serviceChecks, ServiceCheckDTO.class);
    }
    
}
