package cz.muni.fi.pa165.carparkmanager.service.facade;

import cz.muni.fi.pa165.carparkmanager.api.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.ServiceCheckFacade;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import cz.muni.fi.pa165.carparkmanager.service.ServiceCheckService;
import cz.muni.fi.pa165.carparkmanager.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.carparkmanager.service.utils.DataMapper;
import cz.muni.fi.pa165.carparkmanager.service.utils.DataMapperImpl;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Juřena
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EmployeeFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ServiceCheckService serviceCheckService;

    @InjectMocks
    private final ServiceCheckFacade serviceCheckFacade = new ServiceCheckFacadeImpl();

    @Spy
    @Autowired
    private final DataMapper dataMapper = new DataMapperImpl();

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private ServiceCheck serviceCheck;

    private ServiceCheckDTO serviceCheckDTO;

    @BeforeMethod
    public void prepare() {

        Date date = new Date();

        serviceCheck = new ServiceCheck();
        serviceCheck.setId(new Long(1));
        serviceCheck.setIntervalFrom(date);
        serviceCheck.setIntervalTo(date);
        serviceCheck.setDone(true);
        serviceCheck.setDoneWhen(date);

        serviceCheckDTO = new ServiceCheckDTO();
        serviceCheckDTO.setId(new Long(1));
        serviceCheckDTO.setIntervalFrom(date);
        serviceCheckDTO.setIntervalTo(date);
        serviceCheckDTO.setDone(true);
        serviceCheckDTO.setDoneWhen(date);

    }

    @Test
    public void createService() {
        serviceCheckFacade.create(serviceCheckDTO);
        verify(serviceCheckService).create(any(ServiceCheck.class));
    }

    @Test
    public void updateTest() {
        serviceCheckFacade.update(serviceCheckDTO);
        verify(serviceCheckService).update(any(ServiceCheck.class));
    }

    @Test
    public void deleteTest() {
        serviceCheckFacade.delete(serviceCheckDTO);
        verify(serviceCheckService).delete(serviceCheck);
    }

    @Test
    public void findById() {
        when(serviceCheckService.findById(1L)).thenReturn(serviceCheck);
        ServiceCheckDTO serviceCheckDTOx = serviceCheckFacade.findById(serviceCheck.getId());
        assertNotNull(serviceCheckDTOx);
        assertEquals(serviceCheckDTOx.getId(), serviceCheckDTO.getId());
    }

    @Test
    public void findAllServiceTest() {
        when(serviceCheckService.findAll()).thenReturn(Arrays.asList(serviceCheck));
        List<ServiceCheckDTO> drives = serviceCheckFacade.findAll();
        verify(serviceCheckService).findAll();
        assertNotNull(drives);
        assertEquals(drives.get(0).getId(), serviceCheck.getId());
    }

}
