package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author xbonco1
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void create(Employee employee) {
        try {
            employeeDao.create(employee);
        } catch (Exception e) {
            throw new DataAccessException("Cannot create employee: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void update(Employee employee) {
        try {
            employeeDao.update(employee);
        } catch (Exception e) {
            throw new DataAccessException("Cannot update employee: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void delete(Employee employee) {
        try {
            employeeDao.delete(employee);
        } catch (Exception e) {
            throw new DataAccessException("Cannot delete employee: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public Employee findById(Long id) {
        try {
            return employeeDao.findById(id);
        } catch (Exception e) {
            throw new DataAccessException("Cannot find employee by Id: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<Employee> findAll() {
        try {
            return employeeDao.findAll();
        } catch (Exception e) {
            throw new DataAccessException("Cannot findAll employee: " + e.getMessage(), e) {
            };
        }
    }
}
