/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appone.jpa;

import appone.common.Util;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Xander
 */
@Entity
public class MoveType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "MINUTES")
    private int minutes;

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getMinutesFormatted(){
        return Util.formatAsTimeString(minutes);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        
        if (object instanceof Long){
            return (id == (long)object);
        }
        
        if (!(object instanceof MoveType)) {
            return false;
        }
        MoveType other = (MoveType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "appone.jpa.MoveType[ id=" + id + " ]";
    }
    
    
}
