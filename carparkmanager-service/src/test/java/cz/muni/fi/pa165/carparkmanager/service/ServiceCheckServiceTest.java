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
 * @author Roman Nedelka
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ServiceCheckServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ServiceCheckDao serviceCheckDao;

    @InjectMocks
    private final ServiceCheckService serviceCheckService = new ServiceCheckServiceImpl();

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private ServiceCheck serviceCheck1;
    private ServiceCheck serviceCheck2;

    @BeforeMethod
    public void prepare() {
        Date date = new Date();

        serviceCheck1 = new ServiceCheck();
        serviceCheck1.setId(10L);
        serviceCheck1.setDone(true);
        serviceCheck1.setDoneWhen(date);
        serviceCheck1.setIntervalFrom(date);
        serviceCheck1.setIntervalTo(date);

        serviceCheck2 = new ServiceCheck();
        serviceCheck2.setId(10L);
        serviceCheck2.setDone(true);
        serviceCheck2.setDoneWhen(date);
        serviceCheck2.setIntervalFrom(date);
        serviceCheck2.setIntervalTo(date);
    }

    @Test
    public void createServiceCheckTest() {
        serviceCheckService.create(serviceCheck1);
        verify(serviceCheckDao).create(serviceCheck1);
    }

    @Test
    public void updateServiceCheckTest() {
        serviceCheckService.update(serviceCheck1);
        verify(serviceCheckDao).update(any(ServiceCheck.class));
    }

    @Test
    public void deleteServiceCheckTest() {
        serviceCheckService.delete(serviceCheck1);
        verify(serviceCheckDao).delete(any(ServiceCheck.class));
    }

    @Test
    public void findServiceCheckByIdTest() {
        when(serviceCheckDao.findById(1L)).thenReturn(serviceCheck1);
        Assert.assertEquals(serviceCheckService.findById(1L), serviceCheck1);

        when(serviceCheckDao.findById(0L)).thenReturn(null);
        Assert.assertEquals(serviceCheckService.findById(0L), null);
    }

    @Test
    public void findAllServiceChecks() {
        when(serviceCheckDao.findAll()).thenReturn(Arrays.asList(serviceCheck1, serviceCheck2));
        final List<ServiceCheck> serviceChecks = serviceCheckService.findAll();
        Assert.assertEquals(2, serviceChecks.size());
    }
}
