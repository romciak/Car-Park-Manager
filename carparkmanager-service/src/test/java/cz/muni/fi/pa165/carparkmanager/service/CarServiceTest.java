package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.service.config.ServiceConfiguration;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.CarDao;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.DriveDao;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.ServiceCheckDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import cz.muni.fi.pa165.carparkmanager.persistence.enums.ClassificationOfEmployeesEnum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.when;

/**
 *
 * @author Jaroslav Bonco
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CarServiceTest extends AbstractTestNGSpringContextTests {

    private static final long ONE_MINUTE_IN_MILIS = 600000;

    @Mock
    private CarDao carDao;

    @Mock
    private ServiceCheckDao serviceCheckDao;

    @Mock
    private DriveDao driveDao;

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private final CarService carService = new CarServiceImpl();

    @InjectMocks
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @InjectMocks
    private final ServiceCheckService serviceCheckService = new ServiceCheckServiceImpl();

    @InjectMocks
    private final DriveService driveService = new DriveServiceImpl();

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Car car1;
    private Car car2;
    private ServiceCheck serviceCheck1;
    private Employee emp;

    @BeforeMethod
    public void prepare() {

        Date date = new Date();
        Date datePlusSixMonths = DateUtils.addMonths(date, 6);

        serviceCheck1 = new ServiceCheck();
        serviceCheck1.setId(10L);
        serviceCheck1.setDone(true);
        serviceCheck1.setDoneWhen(date);
        serviceCheck1.setIntervalFrom(date);
        serviceCheck1.setIntervalTo(datePlusSixMonths);

        car1 = new Car();
        car2 = new Car();

        car1.setId(1L);
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

        List serviceChecks = new ArrayList();
        serviceChecks.add(serviceCheck1);
        car2.setServiceCheckList(serviceChecks);

        emp = new Employee();
        emp.setBirthDate(new Date());
        emp.setClassification(ClassificationOfEmployeesEnum.MANAGER);
        emp.setFirstname("Roman");
        emp.setSurname("Nedelka");
        emp.setId(1L);
        emp.setDriveList(null);
    }

    @Test
    public void createCarTest() {
        carService.create(car1);
        verify(carDao).create(car1);
    }

    @Test
    public void updateCarTest() {
        carService.update(car1);
        verify(carDao).update(any(Car.class));
    }

    @Test
    public void deleteCarTest() {
        carService.delete(car1);
        verify(carDao).delete(any(Car.class));
    }

    @Test
    public void findCarByIdTest() {
        when(carDao.findById(1L)).thenReturn(car1);
        Assert.assertEquals(carService.findById(1L), car1);

        when(carDao.findById(0L)).thenReturn(null);
        Assert.assertEquals(carService.findById(0L), null);
    }

    @Test
    public void findAllCars() {
        when(carDao.findAll()).thenReturn(Arrays.asList(car1, car2));
        final List<Car> cars = carService.findAll();
        Assert.assertEquals(2, cars.size());
    }

    @Test
    public void carWithoutPreviousServiceCheckTest() {
        ServiceCheck sc = carService.checkServiceInterval(car1);
        Assert.assertNotNull(sc);
    }

    @Test
    public void carWithPreviousServiceCheckTest() {
        carDao.create(car2);
        ServiceCheck sc = carService.checkServiceInterval(car2);

        Date date = new Date();
        Date datePlusSixMonths = DateUtils.addMonths(date, 6);

        Assert.assertTrue((datePlusSixMonths.getTime() - sc.getIntervalFrom().getTime()) < ONE_MINUTE_IN_MILIS);
    }
}
