package de.hsos.digitalerzwilling.Cache;

import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.Cache.Exception.ElementNotFoundException;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.DatenKlassen.Element;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author User
 * 
 * getAll -> Funktionaler aufruf möglich.
 */
public abstract class Cache {
    static protected boolean state;
    
    protected Map<Long,Element> elements[];
    
    @Inject
    private Updater updater;
    
    @Inject protected DatenbankSchnittstelle datenbankschnittstelle;

    public Map<Long, Element>[] getElements() {
        return elements;
    }

    public void setElements(Map<Long, Element> elements[]) {
        this.elements = elements;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        Cache.state = state;
    }
    
    public void toggleState() {
        this.datenbankschnittstelle.commitAndBegin();
        state = !state;
    }
    
    public Element getById(Long id) throws ElementNotFoundException{
        if(!(state==true?elements[1]:elements[0]).containsKey(id)) throw new ElementNotFoundException();
        
        return state==true?elements[1].get(id):elements[0].get(id);
    }
    
    public List<Element> getAll() {
        List<Element> elementList = new ArrayList<>();
        Map<Long,Element> currentElemets = state==true?elements[1]:elements[0];
        for(Map.Entry<Long,Element> entry : currentElemets.entrySet()){
            elementList.add(entry.getValue());
        }
        return elementList;
    }
    
    public Cache() {}
    
    @PostConstruct
    public void init(){
        elements = new Map[2];
        elements[0] = new HashMap<>();
        elements[1] = new HashMap<>();
        try {
            this.updateAll();
        } catch (DBErrorException ex) {
            Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
        }
        updater.registerCache(this);
    }

    abstract public void update() throws DBErrorException;
    abstract public void updateAll() throws DBErrorException;
}
