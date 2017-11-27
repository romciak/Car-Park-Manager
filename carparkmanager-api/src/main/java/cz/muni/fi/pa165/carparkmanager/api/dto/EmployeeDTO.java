/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carparkmanager.api.dto;

import java.util.Date;
import cz.muni.fi.pa165.carparkmanager.persistence.enums.ClassificationOfEmployeesEnum;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author xbonco1
 */
public class EmployeeDTO {
    private Long id;
    private String firstname;
    private String surname; 
    private Date birthDate;
    private ClassificationOfEmployeesEnum classification;
    // private List<DriveDTO> driveList;
    
    public EmployeeDTO() {

    }

    public EmployeeDTO(Long id, String firstname, String surname, Date birthDate, ClassificationOfEmployeesEnum classification) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.birthDate = birthDate;
        this.classification = classification;         
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstname() {
        return firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date dateOfBirth) {
        this.birthDate = dateOfBirth;
    }
    
    public ClassificationOfEmployeesEnum getClassification() {
        return classification;
    }

    public void setClassification(ClassificationOfEmployeesEnum classification) {
        this.classification = classification;
    }

    /*
    public List<DriveDTO> getDriveList() {
        return driveList;
    }

    public void setDriveList(List<DriveDTO> driveList) {
        this.driveList = driveList;
    }
    */
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.birthDate);
        hash = 73 * hash + Objects.hashCode(this.firstname);
        hash = 73 * hash + Objects.hashCode(this.surname);
        hash = 73 * hash + Objects.hashCode(this.classification);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof EmployeeDTO)) {
            return false;
        }
        
        final EmployeeDTO other = (EmployeeDTO) obj;
        if (!Objects.equals(this.id, other.getId())) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.getFirstname())) {
            return false;
        }
        if (!Objects.equals(this.surname, other.getSurname())) {
            return false;
        }
        if (!Objects.equals(this.birthDate, other.getBirthDate())) {
            return false;
        }
        if (this.classification != other.getClassification()) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        return "EmployeeDTO{" + "id=" + id + ", firstName=" + firstname + ", surname=" + surname
                + ", birthDate=" + birthDate + ", classification=" + classification + '}';
    }
}
