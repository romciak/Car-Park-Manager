package cz.muni.fi.pa165.carparkmanager.service.facade;

import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.EmployeeFacade;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import cz.muni.fi.pa165.carparkmanager.persistence.enums.ClassificationOfEmployeesEnum;
import cz.muni.fi.pa165.carparkmanager.service.EmployeeService;
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
    private EmployeeService employeeService;

    @InjectMocks
    private final EmployeeFacade employeeFacade = new EmployeeFacadeImpl();

    @Spy
    @Autowired
    private final DataMapper dataMapper = new DataMapperImpl();

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Employee employee;

    private EmployeeDTO employeeDTO;

    @BeforeMethod
    public void prepare() {

        Date date = new Date();

        employee = new Employee();
        employee.setBirthDate(date);
        employee.setClassification(ClassificationOfEmployeesEnum.MANAGER);
        employee.setFirstname("John");
        employee.setSurname("Doe");
        employee.setId(new Long(1));

        employeeDTO = new EmployeeDTO();
        employeeDTO.setBirthDate(date);
        employeeDTO.setClassification(ClassificationOfEmployeesEnum.MANAGER);
        employeeDTO.setFirstname("John");
        employeeDTO.setSurname("Doe");
        employeeDTO.setId(new Long(1));

    }

    @Test
    public void createService() {
        employeeFacade.create(employeeDTO);
        verify(employeeService).create(any(Employee.class));
    }

    @Test
    public void updateTest() {
        employeeFacade.update(employeeDTO);
        verify(employeeService).update(any(Employee.class));
    }

    @Test
    public void deleteTest() {
        employeeFacade.delete(employeeDTO);
        verify(employeeService).delete(employee);
    }

    @Test
    public void findById() {
        when(employeeService.findById(1L)).thenReturn(employee);
        EmployeeDTO employeeDTOx = employeeFacade.findById(employee.getId());
        assertNotNull(employeeDTOx);
        assertEquals(employeeDTOx.getId(), employeeDTO.getId());
    }

    @Test
    public void findAllServiceTest() {
        when(employeeService.findAll()).thenReturn(Arrays.asList(employee));
        List<EmployeeDTO> employees = employeeFacade.findAll();
        verify(employeeService).findAll();
        assertNotNull(employees);
        assertEquals(employees.get(0).getId(), employee.getId());
    }

}
