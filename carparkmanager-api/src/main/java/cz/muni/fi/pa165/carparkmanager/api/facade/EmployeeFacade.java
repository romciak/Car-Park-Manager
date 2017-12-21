package cz.muni.fi.pa165.carparkmanager.api.facade;

import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeAuthenticateDTO;
import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeDTO;
import java.util.List;

/**
 *
 * @author xbonco1
 */
public interface EmployeeFacade {

    /**
     * Method to create an employee
     *
     * @param employee
     */
    public Long create(EmployeeDTO employeeDto, String password);

    /**
     * Method to update an employee
     *
     * @param employee
     */
    public EmployeeDTO update(EmployeeDTO eDTO);

    /**
     * Method to delete an employee
     *
     * @param employee
     */
    void delete(EmployeeDTO employee);

    /**
     * Method to find a certain employee
     *
     * @param id
     * @return found Employee
     */
    EmployeeDTO findById(Long id);

    /**
     * Method to get all employees
     *
     * @return a list of all Employees
     */
    List<EmployeeDTO> findAll();
    
    /**
     * Registers new User and create hash of his password
     *
     * @param employeeDTO Employee to register
     * @param password unencrypted password
     */
    void registerUser(EmployeeDTO employeeDTO, String password);
    
    /**
     * Tries to authenticate user
     * 
     * @param employee employee to authenticate
     * @return true if authentification is succesfull, false otherwise
     */
    EmployeeDTO authenticate(EmployeeAuthenticateDTO employee);
            
    /**
     * Checks whetever employee is an admin
     * 
     * @param employeeDTO employee to check
     * @return true if employee is an admin, false otherwise
     */
    Boolean isAdmin(EmployeeDTO employeeDTO);
    
    public EmployeeDTO findEmployeeByLogin(String login);           
}
