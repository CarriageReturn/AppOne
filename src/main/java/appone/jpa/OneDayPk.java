/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appone.jpa;

import java.io.Serializable;
import java.util.Objects;


/**
 *
 * @author Xander
 */
public class OneDayPk implements Serializable{
    
    private Long wayTo;
    
    private Long midday;
    
    private Long wayBack;
    
    public OneDayPk() {
    }

    public Long getWayTo() {
        return wayTo;
    }

    public void setWayTo(Long wayTo) {
        this.wayTo = wayTo;
    }

    public Long getMidday() {
        return midday;
    }

    public void setMidday(Long midday) {
        this.midday = midday;
    }

    public Long getWayBack() {
        return wayBack;
    }

    public void setWayBack(Long wayBack) {
        this.wayBack = wayBack;
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
        final OneDayPk other = (OneDayPk) obj;
        if (!Objects.equals(this.wayTo, other.wayTo)) {
            return false;
        }
        if (!Objects.equals(this.midday, other.midday)) {
            return false;
        }
        if (!Objects.equals(this.wayBack, other.wayBack)) {
            return false;
        }
        return true;
    }
}
