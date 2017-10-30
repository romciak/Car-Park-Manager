package cz.muni.fi.pa165.carparkmanager.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Drive entity
 * 
 * @author Jakub Juřena
 */
@Entity
public class Drive implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date timeFrom;
    
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date timeTo;
    
    @Column(nullable = true, name = "km")
    private int km;

    @ManyToOne
    @JoinColumn(name = "id_car", referencedColumnName = "id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    private Employee employee;

    public Drive() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    @Override
    public int hashCode() {
        int result = 23;
        result = 67 * result + this.timeFrom.hashCode();
        result = 67 * result + this.timeTo.hashCode();
        result = 67 * result + this.km;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        
        if (obj == null) 
            return false;
        
        if (getClass() != obj.getClass()) 
            return false;
        
        
        final Drive other = (Drive) obj;
        if (!timeFrom.equals(other.timeFrom)) 
            return false;
        if (!timeTo.equals(other.timeTo))
            return false;
        return km == other.km; 
    }

    @Override
    public String toString() {
        return "Drive{" + "id=" + id + ", timeFrom=" + timeFrom
                + ", timeTo=" + timeTo + ", km=" + km + ", car="
                + car + ", employee=" + employee + '}';
    }

}
