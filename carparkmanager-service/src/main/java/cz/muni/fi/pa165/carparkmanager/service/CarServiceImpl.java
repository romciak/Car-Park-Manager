package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.CarDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roman Nedelka
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public void create(Car c) {
        try {
            carDao.create(c);
        } catch (Exception e) {
            throw new DataAccessException("Cannot create Car: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void delete(Car c) {
        try {
            carDao.delete(c);
        } catch (Exception e) {
            throw new DataAccessException("Cannot delete Car: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void update(Car c) {
        try {
            carDao.update(c);
        } catch (Exception e) {
            throw new DataAccessException("Cannot update Car: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public Car findById(Long id) {
        try {
            return carDao.findById(id);
        } catch (Exception e) {
            throw new DataAccessException("Cannot find Car: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<Car> findAll() {
        try {
            return carDao.findAll();
        } catch (Exception e) {
            throw new DataAccessException("Cannot findAll Car: " + e.getMessage(), e) {
            };
        }
    }

}
