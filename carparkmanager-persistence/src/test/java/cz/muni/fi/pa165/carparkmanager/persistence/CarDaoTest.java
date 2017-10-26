package cz.muni.fi.pa165.carparkmanager.persistence;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.CarDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jaroslav Bonco
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CarDaoTest {
    
    @PersistenceContext
    public EntityManager em;

    @Autowired
    private CarDao carDao;

    private Car car1;
    private Car car2;

    @BeforeMethod
    private void init(){        
        car1 = new Car();
        car2 = new Car();
            
        car1.setBrand("ISUZU");
        car1.setType(CarType./*dopln*/);
        car1.setEngineType(EngineType./*dopln*/);
        car1.setKmCount(new BigDecimal("147045"));
        car1.setProductionYear(1995);

        car1.setBrand("BMW");
        car1.setType(CarType./*dopln*/);
        car1.setEngineType(EngineType./*dopln*/);
        car1.setKmCount(new BigDecimal("30000"));
        car1.setProductionYear(2016);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createWithNullTest() {
        carDao.create(null);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createCarWithNullBrandTest(){
        car1.setBrand(null);
        carDao.create(car1);
    }

    @Test
    public void updateCarTest(){
        carDao.create(car1);

        car1.setKmCount(new BigDecimal("160000"));
        carDao.update(car1);
        Assert.assertEquals(carDao.findById(car1.getVin()).getKmCount(), new BigDecimal("160000"));

        car1.setProductionYear(2057);
        carDao.update(car1);
        Assert.assertEquals(carDao.findById(car1.getVin()).getProductionYear(), 2017);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateCarWithNullReference() {
        carDao.create(car1);
        carDao.update(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateCarWithWrongProductionYear() {
        carDao.create(car1);
        car1 = serviceDao.findById(car1.getVin());
        car1.setProductionYear(2020);
        carDao.update(car1);
    }

    @Test
    public void findByVinTest(){
        carDao.create(car1);
        Car found = carDao.findByVin(car1.getVin());
        Assert.assertEquals(found, car1);
        assertDeepEquals(found, car1);
    }

    @Test
    public void findByNonexistingVinTest(){
        Assert.assertNull(carDao.findByVin(new Long(1)));
    }

    @Test
    public void findByBrand(){
        carDao.create(car1);
        carDao.create(car2);
        List<Car> found = carDao.findByBrand("BMW");
        List<Car> found2 = carDao.findByBrand("do not exist");
        Assert.assertEquals(found.size(), 1);
        assertDeepEquals(found.get(0), car2);
        Assert.assertEquals(found2.size(), 0);
    }

    @Test
    public void findAllTest(){
        carDao.create(car1);
        carDao.create(car2);
        List<Car> cars = carDao.findAllCars();
        Assert.assertEquals(cars.size(), 2);
        assertDeepEquals(cars.get(0), car1);
        assertDeepEquals(cars.get(1), car2);
    }

    @Test
    public void deleteTest(){
        carDao.create(car1);
        carDao.create(car2);
        Assert.assertNotNull(carDao.findByVin(car1.getVin()));
        carDao.delete(car1);
        Assert.assertNull(carDao.findByVin(car1.getVin()));
        Assert.assertNotNull(carDao.findByVin(car2.getVin()));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullService() {
        carDao.delete(null);
    }

    private void assertDeepEquals(Car c1, Car c2){
        Assert.assertEquals(c1.getBrand(), c2.getBrand());
        Assert.assertEquals(c1.getVin(), c2.getVin());
        Assert.assertEquals(c1.getType(), c2.getType());
        Assert.assertEquals(c1.getEngineType(), c2.getEngineType());
        Assert.assertEquals(c1.getKmCount(), c2.getKmCount());
        Assert.assertEquals(c1.getProductionYear(), c2.getProductionYear());
    }
}
