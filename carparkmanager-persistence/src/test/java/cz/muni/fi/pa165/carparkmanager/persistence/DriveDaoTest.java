package cz.muni.fi.pa165.carparkmanager.persistence;

import cz.muni.fi.pa165.carparkmanager.persistence.conf.PersistenceApplicationContext;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.DriveDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Josef Marek
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class DriveDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private DriveDao driveDao;
    public static final int DRIVE_COUNT = 1;
    private Drive drive1;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @BeforeMethod
    public void init() throws ParseException {
        drive1 = new Drive();
        drive1.setTimeFrom(sdf.parse("25.10.2017"));
        drive1.setTimeTo(sdf.parse("28.10.2017"));
        drive1.setKm(1038);
        driveDao.create(drive1);
    }

    @Test
    public void testFindDriveById() {
        Assert.assertNotNull(driveDao.findById(drive1.getId()));
    }

    @Test
    public void testFindAllDrive() {
        Assert.assertEquals(DRIVE_COUNT, driveDao.findAll().size());
    }

    @Test
    public void testDeleteDrive() {
        driveDao.delete(drive1);
        Assert.assertNull(driveDao.findById(drive1.getId()));
    }

    @Test
    public void testUpdateDrive() {
        drive1.setKm(1138);
        driveDao.update(drive1);
        Assert.assertEquals(drive1.getKm(), 1138);
    }
}