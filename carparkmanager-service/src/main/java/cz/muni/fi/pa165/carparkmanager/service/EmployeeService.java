/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * @param employee
     */
    void create(Employee employee);

    /**
     * Method to update an employee
     * @param employee
     */ 
    void update(Employee employee);
    
    /**
     * Method to delete an employee
     * @param employee 
     */
    void delete(Employee employee);

    /**
     * Method to find a certain employee
     * @param id
     * @return found Employee
     */
    Employee findById(Long id);

    /**
     * Method to get all employees
     * @return a list of all Employees
     */
    List<Employee> findAll();
}
