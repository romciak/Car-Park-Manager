package cz.muni.fi.pa165.carparkmanager.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;



/**
 *
 * @author Josef Marek
 */

@Entity
public class ServiceCheck implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    
    @NotNull
    @Column(nullable = false, name = "interval_from")
    @Temporal(TemporalType.DATE)
    private Date intervalFrom;
    
    @NotNull
    @Column(nullable = false, name = "interval_to")
    @Temporal(TemporalType.DATE)
    private Date intervalTo;
    
    @NotNull
    @Column(nullable = false)
    private boolean done;
        
    @Column(nullable = true, name = "done_when")
    @Temporal(TemporalType.DATE)
    private Date doneWhen;

    @ManyToOne
    @JoinColumn(name = "id_car", referencedColumnName = "id")
    private Car car;
    
    public ServiceCheck() {
        
    }
    
    public ServiceCheck(Long id, Date intervalFrom, Date intervalTo, Boolean done, Date doneWhen) {
        this.id = id;
        this.intervalFrom = intervalFrom;
        this.intervalTo = intervalTo;
        this.done = done;
        this.doneWhen = doneWhen;         
}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getIntervalFrom() {
        return intervalFrom;
    }

    public void setIntervalFrom(Date IntervalFrom) {
        this.intervalFrom = IntervalFrom;
    }
    
    public Date getIntervalTo() {
        return intervalTo;
    }

    public void setIntervalTo(Date IntervalTo) {
        this.intervalTo = IntervalTo;
    }    
    
     public boolean getDone() {
        return done;
    }

    public void setDone(boolean Done) {
        this.done = Done;
    } 
    
    public Date getDoneWhen() {
        return doneWhen;
    }

    public void setDoneWhen(Date DoneWhen) {
        this.doneWhen = DoneWhen;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
    
    @Override
    public int hashCode() {
        int hash  = 7;
        hash  = 37 * hash  + Objects.hashCode(this.id);
        hash  = 37 * hash  + Objects.hashCode(this.intervalFrom);
        hash  = 37 * hash  + Objects.hashCode(this.intervalTo);
        hash  = 37 * hash  + Objects.hashCode(this.done);
        hash  = 37 * hash  + Objects.hashCode(this.doneWhen);
        return hash ;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServiceCheck other = (ServiceCheck) obj;
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
        return true;
    }

    @Override
    public String toString() {
        return "ServiceCheck{" + "id=" + id + ", intervalFrom="
                + intervalFrom + ", intervalTo=" + intervalTo + ", done="
                + done + ", doneWhen=" + doneWhen + ", car=" + car + '}';
    }

}
