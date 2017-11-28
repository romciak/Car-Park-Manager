package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.api.dto.CarDTO;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import cz.muni.fi.pa165.carparkmanager.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.carparkmanager.service.utils.DataMapper;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author xbonco1
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class DataMapperTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DataMapper dataMapper;

    private Car car;

    private CarDTO carDTO;

    @BeforeClass
    public void setUp() throws ParseException {

        car = new Car();
        car.setVin("1");
        car.setBrand("ISUZU");
        car.setType("Truck");

        carDTO = new CarDTO();
        carDTO.setVin("1");
        carDTO.setBrand("ISUZU");
        carDTO.setType("Truck");
    }

    @Test
    public void carToCarDTOTest() {
        final Car result = dataMapper.mapTo(carDTO, Car.class);
        Assert.assertNotNull(result);
        Assert.assertEquals(carDTO.getVin(), result.getVin());
        Assert.assertEquals(carDTO.getBrand(), result.getBrand());
        Assert.assertEquals(carDTO.getType(), result.getType());
    }

    @Test
    public void carDTOToCarTest() {
        final CarDTO result = dataMapper.mapTo(car, CarDTO.class);
        Assert.assertNotNull(result);
        Assert.assertEquals(car.getVin(), result.getVin());
        Assert.assertEquals(car.getBrand(), result.getBrand());
        Assert.assertEquals(car.getType(), result.getType());
    }
}
