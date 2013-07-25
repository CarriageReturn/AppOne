/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appone.jpa;

import appone.common.Util;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Xander
 */
@Entity
@IdClass(value=OneDayPk.class)
public class OneDay implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "wayto")
    private MoveType wayTo;
   
    @Id
    @ManyToOne
    @JoinColumn(name = "wayback")
    private MoveType wayBack;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "midday")
    private MoveType midday;    

    @Column(name="done")
    private boolean done;
    
    public OneDay() {
    }

    public MoveType getMidday() {
        return midday;
    }

    public void setMidday(MoveType midday) {
        this.midday = midday;
    }
    
    public MoveType getWayTo() {
        return wayTo;
    }

    public void setWayTo(MoveType wayTo) {
        this.wayTo = wayTo;
    }

    public MoveType getWayBack() {
        return wayBack;
    }

    public void setWayBack(MoveType wayBack) {
        this.wayBack = wayBack;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
   
    public int getMinutesSum(){
        
        int sum = 0;
        
        if (wayTo != null){
            sum += wayTo.getMinutes();
        }
        if (midday != null){
            sum += midday.getMinutes();
        }
        if (wayBack != null){
            sum += wayBack.getMinutes();
        }
        
        return sum;
    }
    
    public String getMinutesSumFormatted(){
        
        if (getMinutesSum()==0){
            return "";
        }
        
        return Util.formatAsTimeString(getMinutesSum());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OneDay other = (OneDay) obj;
        if (!Objects.equals(this.wayTo, other.wayTo)) {
            return false;
        }
        if (!Objects.equals(this.wayBack, other.wayBack)) {
            return false;
        }
        if (!Objects.equals(this.midday, other.midday)) {
            return false;
        }
        return true;
    }
}
