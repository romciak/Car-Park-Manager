package cz.muni.fi.pa165.carparkmanager.api.dto;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Jakub Juřena
 */
public class DriveDTO {

    private Long id;
    private Date timeFrom;
    private Date timeTo;
    private int km;
    private CarDTO car;
    private EmployeeDTO employee;

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

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.timeFrom);
        hash = 97 * hash + Objects.hashCode(this.timeTo);
        hash = 97 * hash + this.km;
        hash = 97 * hash + Objects.hashCode(this.car);
        hash = 97 * hash + Objects.hashCode(this.employee);
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
        if (!(obj instanceof DriveDTO)) {
            return false;
        }
        final DriveDTO other = (DriveDTO) obj;
        if (this.km != other.km) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.timeFrom, other.timeFrom)) {
            return false;
        }
        if (!Objects.equals(this.timeTo, other.timeTo)) {
            return false;
        }
        if (!Objects.equals(this.car, other.car)) {
            return false;
        }
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DriveDTO{" + "id=" + id + ", timeFrom=" + timeFrom
                + ", timeTo=" + timeTo + ", km=" + km + ", car=" + car
                + ", employee=" + employee + '}';
    }

}
