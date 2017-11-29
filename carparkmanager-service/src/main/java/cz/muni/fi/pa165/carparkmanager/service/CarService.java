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
     * Non-trivial method. Checks, whether the car has service checks. Checks,
     * if they are only planned or already done. According to this plans the new
     * ones. Also handles collisions of service checks and planned drives -
     * drive can be planned at the same time as a service check - in this case,
     * cancels the drive.
     *
     * @param car car to check
     * @return ServiceCheck null, if planning a service check is unnecessary,
     * ServiceCheck, if the method generated the new service check
     */
    ServiceCheck checkServiceInterval(Car car);

}
