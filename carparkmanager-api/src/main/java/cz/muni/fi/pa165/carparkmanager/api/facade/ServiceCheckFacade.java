package cz.muni.fi.pa165.carparkmanager.api.facade;

import cz.muni.fi.pa165.carparkmanager.api.dto.ServiceCheckDTO;
import java.util.List;

/**
 *
 * @author Jakub Juřena
 */
public interface ServiceCheckFacade {

    /**
     * Creates record and saves it to DB
     *
     * @param serviceCheck service check to persist
     */
    void create(ServiceCheckDTO serviceCheck);

    /**
     * Removes service check from DB
     *
     * @param serviceCheck service check to delete
     */
    void delete(ServiceCheckDTO serviceCheck);

    /**
     * Updates service check in DB
     *
     * @param serviceCheck service check to update
     */
    void update(ServiceCheckDTO serviceCheck);

    /**
     * Finds service check by id.
     *
     * @param id id of search service check
     * @return null if Drive with selected id is not found, Drive otherwise
     */
    ServiceCheckDTO findById(Long id);

    /**
     * Returns all service checks.
     *
     * @return All service checks in DB
     */
    List<ServiceCheckDTO> findAll();
}
