/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carparkmanager.api.facade;

import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeDTO;
import java.util.List;

/**
 *
 * @author xbonco1
 */
public interface EmployeeFacade {
    /**
     * Method to create an employee
     * @param employee
     */
    void create(EmployeeDTO employee);

    /**
     * Method to update an employee
     * @param employee
     */ 
    void update(EmployeeDTO employee);
    
    /**
     * Method to delete an employee
     * @param employee 
     */
    void delete(EmployeeDTO employee);

    /**
     * Method to find a certain employee
     * @param id
     * @return found Employee
     */
    EmployeeDTO findById(Long id);

    /**
     * Method to get all employees
     * @return a list of all Employees
     */
    List<EmployeeDTO> findAll();
}
