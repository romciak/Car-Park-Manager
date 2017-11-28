package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.api.exceptions.CarparkmanagerException;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roman Nedelka
 */
@Service
public interface CarService {

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

    /**
     * Non-trivial method. Allows employees to reserve a drive at given time
     * interval. Checks, whether a car's count of kilometers is not exceeded. As
     * a result, creates a row in the Drive table, related to given employee and
     * car.
     *
     * @param employeeId employee identification
     * @param carId car identification
     * @param from time of a drive from
     * @param to time of a drive to
     * @throws CarparkmanagerException
     */
    public void reserveDrive(long employeeId, long carId, Date from, Date to) throws CarparkmanagerException;

}
