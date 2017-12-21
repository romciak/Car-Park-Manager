/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carparkmanager.persistence.dao;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import cz.muni.fi.pa165.carparkmanager.persistence.enums.ClassificationOfEmployeesEnum;
import java.util.List;

/**
 *
 * @author xbonco1
 */
public interface EmployeeDao {
    
    /**
     * Method to create an employee
     * @param employee
     */
    public Long create (Employee employee);
    
    
    /**
     * Method to update an employee
     * @param employee
     */    
    public Employee update (Employee employee); 
    
    /**
     * Method to delete an employee
     * @param employee 
     */
    public void delete (Employee employee); 
    
    /**
     * Method to find a certain employee
     * @param id
     * @return found Employee
     */
    public Employee findById (Long id);
    
    /**
     * Method to get all employees
     * @return a list of all Employees
     */
    public List<Employee> findAll ();
    
    /**
     * Method to find a certain employee
     * @param email
     * @return found Employee
     */
    public Employee findByEmail (String email);
    
    /**
     * Method to get all employees with a certain fistname
     * @param firstname
     * @return a list of all Employees with a certain firstname
     */
    public List<Employee> findByFirstname(String firstname);

    /**
     * Method to get all employees with a certain surname
     * @param surname
     * @return a list of all Employees with a certain surname
     */
    public List<Employee> findBySurname(String surname);

    /**
     * Method to get all employees with a certain classification
     * @param classification enum value
     * @return a list of all Employees with a certain classification
     */
    List<Employee> findByClassification(ClassificationOfEmployeesEnum classification);
    
    public Employee findByLogin(String login);
}
