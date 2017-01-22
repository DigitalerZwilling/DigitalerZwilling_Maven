/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.Websockets;

import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.Cache.Exception.ElementNotFoundException;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.DatenKlassen.Element;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.Session;

/**
 *
 * @author user
 */
public abstract class WebSocket {
    @Inject
    protected Updater webSocketUpdater;
    
    private Session session;
    private Long id;
    private Boolean registriert;

    public WebSocket() {
        this.registriert = Boolean.FALSE;
    }
    
    public void update() throws IllegalStateException{
        try{
            
            if(id == null){
                // Liste
                session.getBasicRemote().sendText(this.ListToJson(this.getCache().getAll()));
                //session.getBasicRemote().sendText(this.getCache().getAll().toString());
            } else {
                // nur ein Element
                session.getBasicRemote().sendText(this.getCache().getById(id).toJson());
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(WebSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ElementNotFoundException ex) {
            
            Logger.getLogger(WebSocket.class.getName()).log(Level.SEVERE, null, ex);
            try {
                session.close();
            } catch (IOException ex1) {
                Logger.getLogger(WebSocket.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    protected abstract Cache getCache();

    public Boolean getRegistriert() {
        return registriert;
    }

    public void setRegistriert(Boolean registriert) {
        this.registriert = registriert;
    }
    
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.session);
        hash = 59 * hash + Objects.hashCode(this.id);
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WebSocket other = (WebSocket) obj;
        if (!Objects.equals(this.session, other.session)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public String ListToJson(List<Element> list){
        String json = "{\"inhalt\":[";
        
        for(int i=0 ; i<list.size() ; i++){
            json += list.get(i).toJson();
            if(i<list.size()-1)
                json += ',';
        }
        
        json += "]}";
        
        return json;
    }
    
}
