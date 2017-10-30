package cz.muni.fi.pa165.carparkmanager.persistence;

import cz.muni.fi.pa165.carparkmanager.persistence.conf.PersistenceApplicationContext;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.CarDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jaroslav Bonco
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CarDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private CarDao carDao;

    private Car car1;
    private Car car2;

    @BeforeMethod
    private void init(){        
        car1 = new Car();
        car2 = new Car();

        car1.setVin("1");
        car1.setBrand("ISUZU");
        car1.setType("Truck");
        car1.setEngineType("Very good");
        car1.setKmCount(147045);
        car1.setProductionYear("1995");

        car2.setVin("2");
        car2.setBrand("BMW");
        car2.setType("Personal");
        car2.setEngineType("Perfection");
        car2.setKmCount(30000);
        car2.setProductionYear("2016");
    }

    @Test
    public void updateCarTest(){
        carDao.create(car1);
        car1.setKmCount(160000);
        carDao.update(car1);
        Assert.assertEquals(carDao.findById(car1.getId()).getKmCount(), 160000);

        car1.setProductionYear("2017");
        carDao.update(car1);
        Assert.assertEquals(carDao.findById(car1.getId()).getProductionYear(), "2017");
    }

    @Test
    public void deleteCarTest(){
        carDao.create(car1);
        carDao.create(car2);
        Assert.assertNotNull(carDao.findById(car1.getId()));
        carDao.delete(car1);
        Assert.assertNull(carDao.findById(car1.getId()));
        Assert.assertNotNull(carDao.findById(car2.getId()));
    }

    
    @Test
    public void findCarByIdTest(){
        carDao.create(car1);
        Car found = carDao.findById(car1.getId());
        Assert.assertEquals(found, car1);
        assertDeepEquals(found, car1);
    }

    @Test
    public void findAllCarsTest(){
        carDao.create(car1);
        carDao.create(car2);
        List<Car> cars = carDao.findAll();
        Assert.assertEquals(cars.size(), 2);
        assertDeepEquals(cars.get(0), car1);
        assertDeepEquals(cars.get(1), car2);
    }
    
    private void assertDeepEquals(Car c1, Car c2){
        Assert.assertEquals(c1.getId(), c2.getId());
        Assert.assertEquals(c1.getBrand(), c2.getBrand());
        Assert.assertEquals(c1.getVin(), c2.getVin());
        Assert.assertEquals(c1.getType(), c2.getType());
        Assert.assertEquals(c1.getEngineType(), c2.getEngineType());
        Assert.assertEquals(c1.getKmCount(), c2.getKmCount());
        Assert.assertEquals(c1.getProductionYear(), c2.getProductionYear());
    }
}
