package cz.muni.fi.pa165.carparkmanager.persistence.dao;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import java.util.List;

/**
 * Dao with CRUD operations for the Car entity.
 * 
 * @author Roman Nedelka
 */
public interface CarDao {

    /**
     * Creates record and persists it to DB.
     * 
     * @param c 
     */
    void create(Car c);

    /**
     * Removes record from DB.
     * 
     * @param c 
     */
    void delete(Car c);

    /**
     * Updates record.
     * 
     * @param c 
     */
    void update(Car c);

    /**
     * Finds record by id.
     * 
     * @param id
     * @return 
     */
    Car findById(Long id);

    /**
     * Returns all records. 
     * 
     * @return 
     */
    List<Car> findAll();

}
