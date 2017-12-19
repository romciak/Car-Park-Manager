package cz.muni.fi.pa165.carparkmanager.api.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Jakub Juřena
 */
public class ServiceCheckDTO {
    private Long id;
    private Date intervalFrom;
    private Date intervalTo;
    private boolean done;
    private Date doneWhen;
    @JsonManagedReference
    private CarDTO car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getIntervalFrom() {
        return intervalFrom;
    }

    public void setIntervalFrom(Date intervalFrom) {
        this.intervalFrom = intervalFrom;
    }

    public Date getIntervalTo() {
        return intervalTo;
    }

    public void setIntervalTo(Date intervalTo) {
        this.intervalTo = intervalTo;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDoneWhen() {
        return doneWhen;
    }

    public void setDoneWhen(Date doneWhen) {
        this.doneWhen = doneWhen;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.intervalFrom);
        hash = 43 * hash + Objects.hashCode(this.intervalTo);
        hash = 43 * hash + (this.done ? 1 : 0);
        hash = 43 * hash + Objects.hashCode(this.doneWhen);
        hash = 43 * hash + Objects.hashCode(this.car);
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
        if (!(obj instanceof ServiceCheckDTO)) {
            return false;
        }
        final ServiceCheckDTO other = (ServiceCheckDTO) obj;
        if (this.done != other.done) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.intervalFrom, other.intervalFrom)) {
            return false;
        }
        if (!Objects.equals(this.intervalTo, other.intervalTo)) {
            return false;
        }
        if (!Objects.equals(this.doneWhen, other.doneWhen)) {
            return false;
        }
        if (!Objects.equals(this.car, other.car)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ServiceCheckDTO{" + "id=" + id 
                + ", intervalFrom=" + intervalFrom + ", intervalTo="
                + intervalTo + ", done=" + done + ", doneWhen="
                + doneWhen + ", car=" + car + '}';
    }

}
