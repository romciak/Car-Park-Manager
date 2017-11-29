package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.DriveDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
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
public class DriveServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private DriveDao driveDao;

    @InjectMocks
    private final DriveService driveService = new DriveServiceImpl();

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Drive drive1;
    private Drive drive2;

    @BeforeMethod
    public void prepare() {
        Date date = new Date();

        drive1 = new Drive();
        drive1.setId(10L);
        drive1.setKm(10);
        drive1.setTimeFrom(date);
        drive1.setTimeTo(date);

        drive2 = new Drive();
        drive2.setId(11L);
        drive2.setKm(11);
        drive2.setTimeFrom(date);
        drive2.setTimeTo(date);
    }

    @Test
    public void createDriveTest() {
        driveService.create(drive1);
        verify(driveDao).create(drive1);
    }

    @Test
    public void updateDriveTest() {
        driveService.update(drive1);
        verify(driveDao).update(any(Drive.class));
    }

    @Test
    public void deleteDriveTest() {
        driveService.delete(drive1);
        verify(driveDao).delete(any(Drive.class));
    }

    @Test
    public void findDriveByIdTest() {
        when(driveDao.findById(1L)).thenReturn(drive1);
        Assert.assertEquals(driveService.findById(1L), drive1);

        when(driveDao.findById(0L)).thenReturn(null);
        Assert.assertEquals(driveService.findById(0L), null);
    }

    @Test
    public void findAllDrives() {
        when(driveDao.findAll()).thenReturn(Arrays.asList(drive1, drive2));
        final List<Drive> drives = driveService.findAll();
        Assert.assertEquals(2, drives.size());
    }
}
