/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carparkmanager.persistence.dao;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import enums.ClassificationOfEmployee;
import java.util.List;

/**
 *
 * @author xbonco1
 */
public interface EmployeeDao {
    /**
     * Method to create an employee
     * @param employee
     * @return a new Employee
     * @throws IllegalArgumentException 
     */
    
    public Employee create (Employee employee) throws IllegalArgumentException;
    
    /**
     * Method to update an employee
     * @param employee
     * @return updated Employee
     */    
    public Employee update (Employee employee) throws IllegalArgumentException;
    
    /**
     * Method to delete an employee
     * @param employee 
     */    
    public void delete (Employee employee) throws IllegalArgumentException; 
    
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
     * Method to get all employees with a certain fistname
     * @param firstname
     * @return a list of all Employees with a certain firstname
     */
    public List<Employee> findByFirstname(String firstname) throws IllegalArgumentException ;

    /**
     * Method to get all employees with a certain surname
     * @param surname
     * @return a list of all Employees with a certain surname
     */
    public List<Employee> findBySurname(String surname) throws IllegalArgumentException ;

    /**
     * Method to get all employees with a certain classification
     * @param classification enum value
     * @return a list of all Employees with a certain classification
     */
    List<Employee> findByClassification(ClassificationOfEmployee classification) throws IllegalArgumentException ;
}
