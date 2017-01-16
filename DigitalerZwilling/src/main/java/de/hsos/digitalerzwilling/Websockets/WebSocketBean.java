/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.Websockets;

import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DB_Exception;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.websocket.Session;

/**
 *
 * @author user
 */
//@Named
@ApplicationScoped
public class WebSocketBean {
    //@Inject private Conversation conversation;
    private Set<Session> sessions;
    public WebSocketBean(){
        sessions=new ConcurrentSkipListSet<Session>();
    };


    public void add(Session session){
        if ( session!=null) this.sessions.add(session);
    }
    public void delete(Session session){
        if ( session!=null) this.sessions.remove(session);
    }
    
    public void send(@Observes @DB_Exception Exception ex) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!3"+ex.getMessage()+"ex");
        for(Session s:sessions){
            if (s==null){
                sessions.remove(s);
            }
            else{
                if(s.isOpen()){
                    try {
                        s.getBasicRemote().sendText(ex.getClass().getName()+" ist aufgetreten "+ex.getMessage());
                    } catch (IOException ex1) {
                        Logger.getLogger(WebSocketBean.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
                else{
                    sessions.remove(s);
                }
            }
        }
    }
}
