package cz.muni.fi.pa165.carparkmanager.api.facade;

import cz.muni.fi.pa165.carparkmanager.api.dto.CarCreateDTO;
import cz.muni.fi.pa165.carparkmanager.api.dto.CarDTO;
import java.util.List;

/**
 *
 * @author Roman Nedelka
 */
public interface CarFacade {

    /**
     * Creates record and persists it to DB.
     *
     * @param c
     */
    void create(CarCreateDTO c);

    /**
     * Removes record from DB.
     *
     * @param c
     */
    void delete(CarDTO c);

    /**
     * Updates record.
     *
     * @param c
     */
    void update(CarDTO c);

    /**
     * Finds record by id.
     *
     * @param id
     * @return
     */
    CarDTO findById(Long id);

    /**
     * Returns all records.
     *
     * @return
     */
    List<CarDTO> findAll();

}
