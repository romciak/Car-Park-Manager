package cz.muni.fi.pa165.carparkmanager.service.facade;

import cz.muni.fi.pa165.carparkmanager.api.dto.CarDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.CarFacade;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import cz.muni.fi.pa165.carparkmanager.service.CarService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import cz.muni.fi.pa165.carparkmanager.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.carparkmanager.service.utils.DataMapper;
import cz.muni.fi.pa165.carparkmanager.service.utils.DataMapperImpl;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import org.springframework.test.context.ContextConfiguration;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jaroslav Bonco
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CarFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CarService carService;

    @InjectMocks
    private final CarFacade carFacade = new CarFacadeImpl();

    @Spy
    @Autowired
    private final DataMapper dataMapper = new DataMapperImpl();

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Car car1;
    private Car car2;

    private CarDTO carDto1;
    private CarDTO carDto2;

    @BeforeMethod
    public void init() {

        car1 = new Car();
        car2 = new Car();

        carDto1 = new CarDTO();
        carDto2 = new CarDTO();

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

        carDto1.setVin(car1.getVin());
        carDto1.setBrand(car1.getBrand());
        carDto1.setType(car1.getType());
        carDto1.setEngineType(car1.getEngineType());
        carDto1.setKmCount(car1.getKmCount());
        carDto1.setProductionYear(car1.getProductionYear());

        carDto2.setVin(car2.getVin());
        carDto2.setBrand(car2.getBrand());
        carDto2.setType(car2.getType());
        carDto2.setEngineType(car2.getEngineType());
        carDto2.setKmCount(car2.getKmCount());
        carDto2.setProductionYear(car2.getProductionYear());

    }

    @Test
    public void createService() {
        carFacade.create(carDto1);
        verify(carService).create(any(Car.class));
    }

    @Test
    public void updateTest() {
        carFacade.update(carDto2);
        verify(carService).update(any(Car.class));
    }

    @Test
    public void deleteTest() {
        carFacade.delete(carDto2);
        verify(carService).delete(car2);
    }

    @Test
    public void findById() {
        when(carService.findById(1L)).thenReturn(car1);
        CarDTO carDTO = carFacade.findById(car1.getId());
        assertNotNull(carDTO);
        assertEquals(car1.getId(), carDTO.getId());
    }

    @Test
    public void findAllServiceTest() {
        when(carService.findAll()).thenReturn(Arrays.asList(car1, car2));
        List<CarDTO> cars = carFacade.findAll();
        verify(carService).findAll();
        assertNotNull(cars);
        assertEquals(cars.get(0).getId(), car1.getId());
        assertEquals(cars.get(1).getId(), car2.getId());
    }
}
