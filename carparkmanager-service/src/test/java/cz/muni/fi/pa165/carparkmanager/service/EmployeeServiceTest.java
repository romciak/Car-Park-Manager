package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.ServiceCheckDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import cz.muni.fi.pa165.carparkmanager.service.config.ServiceConfiguration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Juřena
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EmployeeServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ServiceCheckDao serviceCheckDao;

    @InjectMocks
    private final ServiceCheckService serviceCheckService = new ServiceCheckServiceImpl();

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private ServiceCheck sc1;
    private ServiceCheck sc2;

    @BeforeMethod
    public void prepare() {
        Date date = new Date();

        sc1 = new ServiceCheck();
        sc1.setId(new Long(1));
        sc1.setIntervalFrom(date);
        sc1.setIntervalTo(date);
        sc1.setDone(true);
        sc1.setDoneWhen(date);

        sc2 = new ServiceCheck();
        sc2.setId(new Long(2));
        sc2.setIntervalFrom(date);
        sc2.setIntervalTo(date);
        sc2.setDone(true);
        sc2.setDoneWhen(date);
    }

    @Test
    public void createEmployeeTest() {
        serviceCheckService.create(sc1);
        verify(serviceCheckDao).create(sc1);
        serviceCheckService.create(sc2);
        verify(serviceCheckDao).create(sc2);
    }

    @Test
    public void updateEmployeeTest() {
        serviceCheckService.update(sc1);
        verify(serviceCheckDao).update(any(ServiceCheck.class));
    }

    @Test
    public void deleteEmployeeTest() {
        serviceCheckService.delete(sc1);
        verify(serviceCheckDao).delete(any(ServiceCheck.class));
    }

    @Test
    public void findEmployeeByIdTest() {
        when(serviceCheckDao.findById(1L)).thenReturn(sc1);
        Assert.assertEquals(serviceCheckService.findById(1L), sc1);
        when(serviceCheckDao.findById(2L)).thenReturn(sc2);
        Assert.assertEquals(serviceCheckService.findById(2L), sc2);

        when(serviceCheckDao.findById(0L)).thenReturn(null);
        Assert.assertEquals(serviceCheckService.findById(0L), null);
    }

    @Test
    public void findAllEmployees() {
        when(serviceCheckDao.findAll()).thenReturn(Arrays.asList(sc1, sc2));
        final List<ServiceCheck> serviceChecks = serviceCheckService.findAll();
        Assert.assertEquals(2, serviceChecks.size());
    }
}
