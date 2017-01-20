/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.Websockets;

import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DB_Exception;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;


/**
 *
 * @author user
 */
@ApplicationScoped
public class ExceptionEventHandlerScope {
    private final Set<ExceptionWebSocket> sessions;
    public ExceptionEventHandlerScope(){
        sessions=new ConcurrentSkipListSet<>();
    };


    public void add(ExceptionWebSocket session){
        if ( session!=null) this.sessions.add(session);
        else System.out.println("session null");
        System.out.println(this.sessions.contains(session));
    }
    public void delete(ExceptionWebSocket session){
        if ( session!=null) this.sessions.remove(session);
    }
    
    public void send(@Observes @DB_Exception Exception ex) {
        ExceptionWebSocket s[]=this.sessions.toArray(new ExceptionWebSocket[this.sessions.size()]);
        
        for (ExceptionWebSocket item : s) {
            System.out.println(item.toString());
            if (item.getSession() == null) {
                sessions.remove(item);
            } else {
                if (item != null) {
                    item.addToMap(ex);
                } else {
                    sessions.remove(item);
                }
            }
        }
    }
}
