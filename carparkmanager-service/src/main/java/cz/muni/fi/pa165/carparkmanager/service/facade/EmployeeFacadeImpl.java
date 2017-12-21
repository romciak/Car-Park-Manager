package cz.muni.fi.pa165.carparkmanager.service.facade;

import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeAuthenticateDTO;
import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.EmployeeFacade;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import cz.muni.fi.pa165.carparkmanager.service.EmployeeService;
import cz.muni.fi.pa165.carparkmanager.service.utils.DataMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xbonco1
 */
@Service
@Transactional
public class EmployeeFacadeImpl implements EmployeeFacade {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DataMapper mapper;

    @Override
    public Long create(EmployeeDTO employeeDto, String password) {
        Employee employeeEntity = mapper.mapTo(employeeDto, Employee.class);
        return employeeService.create(employeeEntity, password);
    }

    @Override
    public void delete(EmployeeDTO eDTO) {
        Employee employee = mapper.mapTo(eDTO, Employee.class);
        employeeService.delete(employee);
    }

    @Override
    public EmployeeDTO update(EmployeeDTO eDTO) {
        Employee employeeEntity;
        employeeEntity = employeeService.update(mapper.mapTo(eDTO, Employee.class));
        return mapper.mapTo(employeeEntity, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO findById(Long id) {
        Employee employee = employeeService.findById(id);
        return mapper.mapTo(employee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> cars = employeeService.findAll();
        return mapper.mapTo(cars, EmployeeDTO.class);
    }


    @Override
    public void registerUser(EmployeeDTO employeeDTO, String password) {
        if (employeeDTO == null){
            throw new IllegalArgumentException("employeeDTO is null");
        }

        Employee employee = mapper.mapTo(employeeDTO, Employee.class);

        if (employee == null) {
            throw new IllegalArgumentException("employee is null");
        }

        employeeService.registerEmployee(employee, password);
    }
    
   
    @Override
    public EmployeeDTO authenticate(EmployeeAuthenticateDTO employeeAuth) {
        if (employeeAuth == null)
            throw new IllegalArgumentException("employee is null");
        
        Employee employee = employeeService.findByEmail(employeeAuth.getEmail());
        if (employee == null) {
            return null;
        }
        
        if ( !employeeService.authenticate(employee, employeeAuth.getPasswordHash())) {
            return null;
        }
        
        return mapper.mapTo(employee, EmployeeDTO.class);
    }

    @Override
    public Boolean isAdmin(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) 
            throw new IllegalArgumentException("EmployeeDTO is null");
        
        Employee employee = mapper.mapTo(employeeDTO, Employee.class);
        
        if (employee == null) {
            throw new IllegalArgumentException("employee is null");
        }
        
        return employeeService.isAdmin(employee);
    }
    
    @Override
    public EmployeeDTO findEmployeeByLogin(String login) {
       Employee employee = employeeService.findEmployeeByLogin(login);
       if(employee == null){
           return null;
                   }
       else{
           return mapper.mapTo(employee, EmployeeDTO.class);
       }
    }
}
