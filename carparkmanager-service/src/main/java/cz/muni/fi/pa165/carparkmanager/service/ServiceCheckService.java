package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Juřena
 */
@Service
public interface ServiceCheckService {
    /**
     * Creates record and saves it to DB
     * 
     * @param serviceCheck service check to persist
     */
    void create(ServiceCheck serviceCheck);
    
    /**
     * Removes service check from DB
     * 
     * @param serviceCheck service check to delete
     */
    void delete(ServiceCheck serviceCheck);
    
    /**
     * Updates service check in DB
     * 
     * @param serviceCheck service check to update
     */
    void update(ServiceCheck serviceCheck);
    
    /**
     * Finds service check by id.
     * 
     * @param id id of search service check 
     * @return null if Drive with selected id is not found, Drive otherwise
     */
    ServiceCheck findById(Long id);
    
    /**
     * Returns all service checks.
     * 
     * @return All service checks in DB
     */
    List<ServiceCheck> findAll();
}
