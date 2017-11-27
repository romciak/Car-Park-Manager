package cz.muni.fi.pa165.carparkmanager.service.facade;

import cz.muni.fi.pa165.carparkmanager.api.dto.DriveDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.DriveFacade;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
import cz.muni.fi.pa165.carparkmanager.service.DriveService;
import cz.muni.fi.pa165.carparkmanager.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.carparkmanager.service.utils.DataMapper;
import cz.muni.fi.pa165.carparkmanager.service.utils.DataMapperImpl;
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
import org.mockito.Spy;
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
 * @author Roman Nedelka
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class DriveFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private DriveService driveService;

    @InjectMocks
    private final DriveFacade driveFacade = new DriveFacadeImpl();

    @Spy
    @Autowired
    private final DataMapper dataMapper = new DataMapperImpl();

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Drive drive;

    private DriveDTO driveDTO;

    @BeforeMethod
    public void init() {

        Date date = new Date();

        drive = new Drive();
        drive.setId(Long.MIN_VALUE);
        drive.setKm(0);
        drive.setTimeFrom(date);
        drive.setTimeTo(date);

        driveDTO = new DriveDTO();
        driveDTO.setId(Long.MIN_VALUE);
        driveDTO.setKm(0);
        driveDTO.setTimeFrom(date);
        driveDTO.setTimeTo(date);

    }

    @Test
    public void createService() {
        driveFacade.create(driveDTO);
        verify(driveService).create(any(Drive.class));
    }

    @Test
    public void updateTest() {
        driveFacade.update(driveDTO);
        verify(driveService).update(any(Drive.class));
    }

    @Test
    public void deleteTest() {
        driveFacade.delete(driveDTO);
        verify(driveService).delete(drive);
    }

    @Test
    public void findById() {
        when(driveService.findById(Long.MIN_VALUE)).thenReturn(drive);
        DriveDTO driveDTOx = driveFacade.findById(drive.getId());
        assertNotNull(driveDTOx);
        assertEquals(driveDTOx.getId(), driveDTOx.getId());
    }

    @Test
    public void findAllServiceTest() {
        when(driveService.findAll()).thenReturn(Arrays.asList(drive));
        List<DriveDTO> drives = driveFacade.findAll();
        verify(driveService).findAll();
        assertNotNull(drives);
        assertEquals(drives.get(0).getId(), drive.getId());
    }

}
