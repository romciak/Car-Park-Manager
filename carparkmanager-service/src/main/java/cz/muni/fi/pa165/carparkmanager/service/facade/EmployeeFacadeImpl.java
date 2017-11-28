package cz.muni.fi.pa165.carparkmanager.service.facade;

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
    public void create(EmployeeDTO eDTO) {
        Employee employee = mapper.mapTo(eDTO, Employee.class);
        employeeService.create(employee);
    }

    @Override
    public void delete(EmployeeDTO eDTO) {
        Employee employee = mapper.mapTo(eDTO, Employee.class);
        employeeService.delete(employee);
    }

    @Override
    public void update(EmployeeDTO eDTO) {
        Employee employee = mapper.mapTo(eDTO, Employee.class);
        employeeService.update(employee);
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
}
