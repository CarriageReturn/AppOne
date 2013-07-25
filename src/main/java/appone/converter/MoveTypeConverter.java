/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appone.converter;

import appone.controller.DataProducer;
import appone.jpa.MoveType;
import java.util.List;
import javax.enterprise.context.RequestScoped;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Xander
 */
@FacesConverter("net.appone.MoveTypeConverter")
@RequestScoped
public class MoveTypeConverter implements Converter{
    
    @PersistenceContext 
    private EntityManager em;
    
    @Inject
    private DataProducer dataProducer;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        List<MoveType> moveTypes = dataProducer.getMoveTypes();
                        
        for (MoveType moveType : moveTypes) {
            if(moveType.getId() == Long.parseLong(value)){
                return moveType;
            }
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
    
}
