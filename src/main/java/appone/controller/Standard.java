/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appone.controller;

import appone.jpa.MoveType;
import appone.jpa.OneDay;
import appone.jpa.OneDayPk;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Xander
 */
@Named(value = "standard")
@SessionScoped
public class Standard implements Serializable{
    
    private List<OneDay> days;
    
    private DataModel<OneDay> daysModel;
    
    private OneDay oneDay;
    
    private OneDay currentDay;
    
    private int alreadyPlannedCounter = 0;
    
    @Inject
    private DataProducer dataProducer; 
    
            /**
     * <p>The transaction resource.</p>
     */
    @Resource 
    private UserTransaction utx;
    
    /**
     * <p>The <code>PersistenceContext</code>.</p>
     */
    @PersistenceContext 
    private EntityManager em;
    
    @PostConstruct
    private void init(){
        oneDay = new OneDay();
        
        days = dataProducer.getAllDays();
        daysModel = new ListDataModel<>(days);
    }  

    public DataModel<OneDay> getDaysModel() {
        return daysModel;
    }
    
    public List<MoveType> getMoveTypes() {
        Query createQuery = em.createQuery("SELECT m FROM MoveType m");
        return createQuery.getResultList();
    }

    public OneDay getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(OneDay currentDay) {
        
        System.out.print("test");
        
        this.currentDay = currentDay;
    }

    public OneDay getOneDay() {
        return oneDay;
    }

    public void setOneDay(OneDay oneDay) {
        this.oneDay = oneDay;
    }
    
    /**
     * Creates a new instance of Standard
     */
    public Standard() {
    }
    
    public void add() throws SystemException, NotSupportedException, RollbackException, HeuristicMixedException, SecurityException, HeuristicRollbackException{
        
        utx.begin();

        em.persist(oneDay);

        try {

            utx.commit();

            FacesMessage message = new FacesMessage("Okay, läuft.");

            FacesContext.getCurrentInstance().addMessage("addForm:addButton", message);
            
            days.add(oneDay);

            oneDay = new OneDay();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException e) {
            
            FacesMessage message;
 
            if (alreadyPlannedCounter == 0){
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alex, das haste schon vor.", null);
            } else{
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alex, das auch.", null);
            }
            
            FacesContext.getCurrentInstance().addMessage("addForm:addButton", message);
            
            System.err.println("eror");
            
            alreadyPlannedCounter++;
        }
    }
        
    public void madeIt(ValueChangeEvent e)throws Exception{

        utx.begin();
        
        daysModel.getRowData().setDone((boolean)e.getNewValue());
        
        em.merge(daysModel.getRowData());
        
        if(((boolean)e.getNewValue())==true){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fetto! Glückwunsch :-)", null);
            FacesContext.getCurrentInstance().addMessage("addForm:addButton", message);
        }
    
        utx.commit();

    }
    
    public void deleteDay(OneDay day) throws NotSupportedException, SystemException{
        
        
        
        utx.begin();
    
        OneDay entity = em.merge(day);
        em.remove(entity);
        
        try {
            utx.commit();
            
            FacesMessage message = new FacesMessage("Jawohl, weg damit.");

            FacesContext.getCurrentInstance().addMessage("addForm:addButton", message);
            
            days.remove(day);
            
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {  
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hmm, konnte nicht gelöscht werden?!", null);
            FacesContext.getCurrentInstance().addMessage("addForm:addButton", message);
        }
        
    
    }
}
