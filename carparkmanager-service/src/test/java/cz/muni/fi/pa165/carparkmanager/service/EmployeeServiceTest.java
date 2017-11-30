package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import cz.muni.fi.pa165.carparkmanager.persistence.enums.ClassificationOfEmployeesEnum;
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
    private EmployeeDao employeeDao;

    @InjectMocks
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Employee em1;
    private Employee em2;

    @BeforeMethod
    public void prepare() {
        Date date = new Date();

        em1 = new Employee();
        em1.setBirthDate(date);
        em1.setClassification(ClassificationOfEmployeesEnum.MANAGER);
        em1.setFirstname("John");
        em1.setSurname("Doe");
        em1.setId(new Long(1));

        em2 = new Employee();
        em2.setBirthDate(date);
        em2.setClassification(ClassificationOfEmployeesEnum.VOLUNTEER);
        em2.setFirstname("Jane");
        em2.setSurname("Doe");
        em2.setId(new Long(2));
    }

    @Test
    public void createEmployeeTest() {
        employeeService.create(em1);
        verify(employeeDao).create(em1);
        employeeService.create(em2);
        verify(employeeDao).create(em2);
    }

    @Test
    public void updateEmployeeTest() {
        employeeService.update(em1);
        verify(employeeDao).update(any(Employee.class));
    }

    @Test
    public void deleteEmployeeTest() {
        employeeService.delete(em1);
        verify(employeeDao).delete(any(Employee.class));
    }

    @Test
    public void findEmployeeByIdTest() {
        when(employeeDao.findById(1L)).thenReturn(em1);
        Assert.assertEquals(employeeService.findById(1L), em1);
        when(employeeDao.findById(2L)).thenReturn(em2);
        Assert.assertEquals(employeeService.findById(2L), em2);

        when(employeeDao.findById(0L)).thenReturn(null);
        Assert.assertEquals(employeeService.findById(0L), null);
    }

    @Test
    public void findAllEmployees() {
        when(employeeDao.findAll()).thenReturn(Arrays.asList(em1, em2));
        final List<Employee> Employees = employeeService.findAll();
        Assert.assertEquals(2, Employees.size());
    }
}
