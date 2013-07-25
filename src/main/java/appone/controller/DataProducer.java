/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appone.controller;

import appone.jpa.MoveType;
import appone.jpa.OneDay;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Xander
 */
@Named(value = "dataProducer")
@SessionScoped
public class DataProducer implements Serializable {

    /**
     * Creates a new instance of DataProducer
     */
    public DataProducer() {
    }
    
    @PersistenceContext 
    private EntityManager em;
    
    @Produces
    public List<MoveType> getMoveTypes(){
        Query createQuery = em.createQuery("SELECT m FROM MoveType m");
        return createQuery.getResultList();
    }
    
    @Produces
    public List<OneDay> getAllDays(){
        Query createQuery = em.createQuery("SELECT o FROM OneDay o");
        return createQuery.getResultList();
    }
}
