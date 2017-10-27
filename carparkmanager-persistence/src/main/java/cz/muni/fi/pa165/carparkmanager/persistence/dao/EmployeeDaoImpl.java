/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carparkmanager.persistence.dao;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import cz.muni.fi.pa165.carparkmanager.persistence.enums.ClassificationOfEmployeesEnum;
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
    public void create(Employee employee) {
        em.persist(employee);
    }

    @Override
    public void update(Employee employee) {
        em.merge(employee);
    }

    @Override
    public void delete(Employee employee) {
        em.remove(em.contains(employee) ? employee : em.merge(employee));
    }

    @Override
    public Employee findById(Long id) {
        return em.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAll() {
        return em.createQuery("SELECT employee FROM Employee employee", Employee.class).getResultList();
    }

    @Override
    public List<Employee> findByFirstname(String firstname) {
        return em.createQuery("SELECT employee FROM Employee employee WHERE employee.firstname = :firstname")
                .setParameter("firstname", firstname)
                .getResultList();
    }

    @Override
    public List<Employee> findBySurname(String surname) {
        return em.createQuery("SELECT employee FROM Employee employee WHERE employee.surname = :surname")
                .setParameter("surname", surname)
                .getResultList();
    }

    @Override
    public List<Employee> findByClassification(ClassificationOfEmployeesEnum classification) {
        return em.createQuery("SELECT employee FROM Employee employee WHERE employee.classification = :classification")
                .setParameter("classification", classification)
                .getResultList();
    }
}
