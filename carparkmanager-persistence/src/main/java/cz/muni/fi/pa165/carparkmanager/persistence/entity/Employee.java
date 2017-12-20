package cz.muni.fi.pa165.carparkmanager.persistence.entity;

import cz.muni.fi.pa165.carparkmanager.persistence.enums.ClassificationOfEmployeesEnum;
import cz.muni.fi.pa165.carparkmanager.persistence.enums.UserRoleEnum;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author xbonco1
 */
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private String firstname;

    @NotNull
    @Column(nullable = false)
    private String surname; 
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @NotNull
    @Enumerated
    private ClassificationOfEmployeesEnum classification;
    
    
    private String passwordHash;
    
    @Enumerated
    private UserRoleEnum userRole;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = {
        CascadeType.ALL
    })
    private List<Drive> driveList;

    public Employee() {

    }

    public Employee(Long id, String firstname, String surname, Date birthDate, ClassificationOfEmployeesEnum classification) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.birthDate = birthDate;
        this.classification = classification;         
    }
   
    public boolean isAdmin() {
        return this.getUserRole().equals(UserRoleEnum.ADMINISTRATOR);
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

    public List<Drive> getDriveList() {
        return driveList;
    }

    public void setDriveList(List<Drive> driveList) {
        this.driveList = driveList;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
    }
    
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

        if (!(obj instanceof Employee)) {
            return false;
        }
        
        final Employee other = (Employee) obj;
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
}
