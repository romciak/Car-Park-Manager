package cz.muni.fi.pa165.carparkmanager.persistence;

import cz.muni.fi.pa165.carparkmanager.persistence.conf.PersistenceApplicationContext;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import cz.muni.fi.pa165.carparkmanager.persistence.enums.ClassificationOfEmployeesEnum;
import cz.muni.fi.pa165.carparkmanager.persistence.enums.UserRoleEnum;
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
 * @author Roman Nedelka
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class EmployeeDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private EmployeeDao employeeDao;

    public static final int EMPPLOYEES_COUNT = 1;

    private Employee employee1;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @BeforeMethod
    public void init() throws ParseException {
        employee1 = new Employee();
        employee1.setBirthDate(sdf.parse("01.01.1980"));
        employee1.setClassification(ClassificationOfEmployeesEnum.ENGINEER);
        employee1.setFirstname("Jan");
        employee1.setSurname("Novak");
        employee1.setLogin("emp.one");
        employee1.setUserRole(UserRoleEnum.USER);
        employeeDao.create(employee1);
    }

    @Test
    public void testFindEmployeeById() {
        Assert.assertNotNull(employeeDao.findById(employee1.getId()));
    }

    @Test
    public void testFindEmployeeByFirstName() {
        Assert.assertNotNull(employeeDao.findByFirstname("Jan"));
    }

    @Test
    public void testFindEmployeeBySurname() {
        Assert.assertNotNull(employeeDao.findBySurname("Novak"));
    }

    @Test
    public void testFindEmployeeByClsf() {
        Assert.assertNotNull(employeeDao.findByClassification(ClassificationOfEmployeesEnum.ENGINEER));
    }
    
    @Test
    public void testFindAllEmployee() {
        Assert.assertEquals(EMPPLOYEES_COUNT, employeeDao.findAll().size());
    }

    @Test
    public void testDeleteEmployee() {
        employeeDao.delete(employee1);
        Assert.assertNull(employeeDao.findById(employee1.getId()));
    }

    @Test
    public void testUpdateEmployee() {
        employee1.setFirstname("Jofo");
        employeeDao.update(employee1);
        Assert.assertEquals(employee1.getFirstname(), "Jofo");
    }    
}
