package cz.muni.fi.pa165.carparkmanager.service;

import org.springframework.stereotype.Service;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import java.util.List;

/**
 *
 * @author xbonco1
 */
@Service
public interface EmployeeService {

    /**
     * Method to create an employee
     *
     * @param employee
     */
    void create(Employee employee);

    /**
     * Method to update an employee
     *
     * @param employee
     */
    void update(Employee employee);

    /**
     * Method to delete an employee
     *
     * @param employee
     */
    void delete(Employee employee);

    /**
     * Method to find a certain employee
     *
     * @param id
     * @return found Employee
     */
    Employee findById(Long id);

    /**
     * Method to get all employees
     *
     * @return a list of all Employees
     */
    List<Employee> findAll();
    
    /**
     * Method to find a certain employee
     * @param email
     * @return found Employee
     */
    public Employee findByEmail (String email);
    
    /**
     * Create operation for EmployeeDao
     * @throws NullPointerException when NullPointerException occurs
     * @param employee Employee
     * @param password unencrypted password
     */
    void registerEmployee(Employee employee, String password);
    
    /**
     * Tries to authenticate employee
     * @param employee employe to authenticate
     * @param password password hash
     * @return true if user is authenticated, false otherwise
     */
    boolean authenticate(Employee employee, String password);

    /**
     * Checks whether employee is admin
     * @param employee employee to check
     * @return true if employee is admin, false otherwise
     */
    boolean isAdmin(Employee employee);
}
