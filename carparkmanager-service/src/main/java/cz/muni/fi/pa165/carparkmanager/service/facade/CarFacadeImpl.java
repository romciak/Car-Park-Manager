package cz.muni.fi.pa165.carparkmanager.service.facade;

import cz.muni.fi.pa165.carparkmanager.api.dto.CarCreateDTO;
import cz.muni.fi.pa165.carparkmanager.api.dto.CarDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.CarFacade;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import cz.muni.fi.pa165.carparkmanager.service.CarService;
import cz.muni.fi.pa165.carparkmanager.service.utils.DataMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roman Nedelka
 */
@Service
@Transactional
public class CarFacadeImpl implements CarFacade {

    @Autowired
    private CarService carService;

    @Autowired
    private DataMapper mapper;

    @Override
    public void create(CarCreateDTO c) {
        Car car = mapper.mapTo(c, Car.class);
        carService.create(car);
    }

    @Override
    public void delete(CarDTO c) {
        Car car = mapper.mapTo(c, Car.class);
        carService.delete(car);
    }

    @Override
    public void update(CarDTO c) {
        Car car = mapper.mapTo(c, Car.class);
        carService.update(car);
    }

    @Override
    public CarDTO findById(Long id) {
        Car car = carService.findById(id);
        return mapper.mapTo(car, CarDTO.class);
    }

    @Override
    public List<CarDTO> findAll() {
        List<Car> cars = carService.findAll();
        return mapper.mapTo(cars, CarDTO.class);
    }

}
