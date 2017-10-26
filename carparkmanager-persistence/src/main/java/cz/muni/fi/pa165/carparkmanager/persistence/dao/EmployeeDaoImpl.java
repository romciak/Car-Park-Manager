/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carparkmanager.persistence.dao;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import enums.ClassificationOfEmployee;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author xbonco1
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Employee create(Employee employee) throws IllegalArgumentException {
        if(parametersAreOk(employee))
        {
            em.persist(employee);
            em.flush();
            return employee;
        }
        else
        {
            throw new IllegalArgumentException("Employee cannot have null parameter");
        }
    }

    @Override
    public Employee update(Employee employee) throws IllegalArgumentException {
        if (parametersAreOk(employee)){
            em.merge(employee);
            return employee;
        }
        else
        {
            throw new IllegalArgumentException("Employee cannot have null parameter");
        }
    }

    @Override
    public void delete(Employee employee) throws IllegalArgumentException  {
        if (parametersAreOk(employee)){
            em.remove(em.contains(employee) ? employee : em.merge(employee));
        }
        throw new IllegalArgumentException("Trying to delete null");
    }

    @Override
    public Employee findById(Long id) {
        return em.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAll() {
        return em.createQuery("select person from Person person").getResultList();
    }

    @Override
    public List<Employee> findByFirstname(String firstname) throws IllegalArgumentException {
        if (firstname == null) {
            throw new IllegalArgumentException("Cannot search for null firstname");
        }
        
        return em.createQuery("SELECT employee FROM Employee employee WHERE employee.firstname = :firstname")
                .setParameter("firstname", firstname)
                .getResultList();
    }

    @Override
    public List<Employee> findBySurname(String surname) throws IllegalArgumentException {
        if (surname == null) {
            throw new IllegalArgumentException("Cannot search for null surname");
        }
        
        return em.createQuery("SELECT employee FROM Employee employee WHERE employee.surname = :surname")
                .setParameter("surname", surname)
                .getResultList();
    }

    @Override
    public List<Employee> findByClassification(ClassificationOfEmployee classification) throws IllegalArgumentException  {
        if (classification == null) {
            throw new IllegalArgumentException("Cannot search for classification login");
        }
        
        return em.createQuery("SELECT employee FROM Employee employee WHERE employee.classification = :classification")
                .setParameter("classification", classification).getResultList();
    }
    
    private boolean parametersAreOk(Employee employee)
    {
        if(employee == null || employee.getFirstname() == null || employee.getSurname() == null || employee.getBirthDate() == null || employee.getClassification() == null){
            return false;
        }
        
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(employee.getBirthDate());
        
        int yearOfEmployeeBirth = cal1.get(Calendar.YEAR);
        int currentYear = cal2.get(Calendar.YEAR);
        
        if(currentYear - yearOfEmployeeBirth < 18)
        {
            return false;
        }
        
        return true;
    }
}
