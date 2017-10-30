package cz.muni.fi.pa165.carparkmanager.persistence;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.ServiceCheckDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.testng.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Juřena
 */

@ContextConfiguration(classes=ConfigurationPersistence.class) // TODO
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ServiceCheckDaoTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    public ServiceCheckDao serviceCheckDao;
    
    private ServiceCheck serviceCheck1;
    private ServiceCheck serviceCheck2;
    
    private static final int SERVICE_CHECK_COUNT = 2;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    
    @BeforeMethod
    public void createServiceChecks() throws ParseException {
        serviceCheck1 = new ServiceCheck();
        serviceCheck1.setIntervalFrom(sdf.parse("01.01.2005"));
        serviceCheck1.setIntervalTo(sdf.parse("01.07.2007"));
        serviceCheck1.setDone(true);
        serviceCheck1.setDoneWhen(sdf.parse("20.5.2007"));
        
        serviceCheckDao.create(serviceCheck1);
        
        serviceCheck2 = new ServiceCheck();
        serviceCheck2.setIntervalFrom(sdf.parse("01.07.2007"));
        serviceCheck2.setIntervalTo(sdf.parse("01.01.2010"));
        serviceCheck2.setDone(false);
        
        serviceCheckDao.create(serviceCheck2);
        
    }
    
    @Test
    public void testFindAll() {
        List<ServiceCheck> found = serviceCheckDao.findAll();
        Assert.assertEquals(SERVICE_CHECK_COUNT, found.size());
    }
    
    @Test
    public void testFindById() {
        ServiceCheck found = serviceCheckDao.findById(serviceCheck1.getId());
        Assert.assertNotNull(found);
    }
    
    @Test
    public void testFindNotExistingServiceCheckById() {
        Assert.assertNull(serviceCheckDao.findById(Long.MIN_VALUE));
    }
    
    @Test
    public void testCreateServiceCheck() throws ParseException{
        ServiceCheck serviceCheck = new ServiceCheck();
        serviceCheck.setDone(true);
        Date doneWhen = sdf.parse("01.01.2015");
        Date intervalFrom = sdf.parse("01.01.2013");
        Date intervalTo = sdf.parse("01.01.2017");
        serviceCheck.setDoneWhen(doneWhen);
        serviceCheck.setIntervalFrom(intervalFrom);
        serviceCheck.setIntervalTo(intervalTo);
        
        serviceCheckDao.create(serviceCheck);
        
        Assert.assertNotNull(serviceCheck.getId());
        ServiceCheck found = serviceCheckDao.findById(serviceCheck.getId());
        Assert.assertEquals(doneWhen, found.getDoneWhen());
        Assert.assertEquals(true, found.getDone());
        Assert.assertEquals(intervalFrom, found.getIntervalFrom());
        Assert.assertEquals(intervalTo, found.getIntervalTo());
    }
    
    @Test
    public void testUpdateServiceCheck() throws ParseException{
        serviceCheck2.setDone(true);
        Date newDate = sdf.parse("20.12.2009");
        serviceCheck2.setDoneWhen(newDate);
        serviceCheckDao.update(serviceCheck2);
        
        Assert.assertEquals(serviceCheck2.getDone(), true);
        Assert.assertEquals(serviceCheck2.getDoneWhen(), newDate);
    }
    
    @Test
    public void testDeleteServiceCheck(){
        serviceCheckDao.delete(serviceCheck1);
        Assert.assertNull(serviceCheckDao.findById(serviceCheck1.getId()));
    }
    
}
