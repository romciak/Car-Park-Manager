package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.api.exceptions.CarparkmanagerException;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
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
     * nontrivial-method check if last serviceCheck is older than 4 months, if is older, than generate new one.
     * If in planed serviceCheck is planed any drive, drive will be canceled
     * 
     * 
     * @param car car to check
     * @return ServiceCheck null if planning ServiceCheck is unnecessary, ServiceCheck if method generated new ServiceCheck
     */
    ServiceCheck checkServiceInterval(Car car);

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
