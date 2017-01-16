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
    private Set<ExceptionWebSocket> sessions;
    public WebSocketBean(){
        sessions=new ConcurrentSkipListSet<ExceptionWebSocket>();
    };


    public void add(ExceptionWebSocket session){
        if ( session!=null) this.sessions.add(session);
    }
    public void delete(ExceptionWebSocket session){
        if ( session!=null) this.sessions.remove(session);
    }
    
    public void send(@Observes @DB_Exception Exception ex) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!3"+ex.getMessage()+"ex");
        
        ExceptionWebSocket s[]=this.sessions.toArray(new ExceptionWebSocket[this.sessions.size()]);
        
        for(int i=0;i<s.length;i++){
            if (s[i].getSession()==null){
                sessions.remove(s[i]);
            }
            else{
                if(s[i]!=null){
                        s[i].addToMap(ex);
                }
                else{
                    sessions.remove(s[i]);
                }
            }
        }
    }
}
