/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

package cz.muni.fi.pa165.carparkmanager.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;



/**
 *
 * @author Josef Marek
 */

@Entity
public class ServiceCheck  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date intervalFrom;
    
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date intervalTo;
    
    @NotNull
    @Column(nullable = false)
    private boolean done;
        
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date doneWhen;


    // -----------------
    
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
    
    //--------------------
    
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

    //--------------------
    
     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof ServiceCheck)) {
            return false;
        }
        
        final ServiceCheck other = (ServiceCheck) obj;
        if (!Objects.equals(this.id, other.getId())) {
            return false;
        }
        if (!Objects.equals(this.intervalFrom , other.getIntervalFrom())) {
            return false;
        }
        if (!Objects.equals(this.intervalTo, other.getIntervalTo())) {
            return false;
        }
        if (!Objects.equals(this.done, other.getDone())) {
            return false;
        }
        
        else if (!Objects.equals(this.doneWhen, other.getDoneWhen())) {
            return false;
        }
       
        return true;
     }
    
    
    
}
    
    
    

