package cz.muni.fi.pa165.carparkmanager.persistence.dao;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import java.util.List;

/** 
 * @author Josef Marek
 */
public interface ServiceCheckDao {

    /**
     * Creates record and persists it to DB.
     * 
     * @param c 
     */
    void create(ServiceCheck c);

    /**
     * Removes record from DB.
     * 
     * @param c 
     */
    void delete(ServiceCheck c);

    /**
     * Updates record.
     * 
     * @param c 
     */
    void update(ServiceCheck c);

    /**
     * Finds record by id.
     * 
     * @param id
     * @return 
     */
    ServiceCheck findById(Long id);

    /**
     * Returns all records. 
     * 
     * @return 
     */
    List<ServiceCheck> findAll();

}
