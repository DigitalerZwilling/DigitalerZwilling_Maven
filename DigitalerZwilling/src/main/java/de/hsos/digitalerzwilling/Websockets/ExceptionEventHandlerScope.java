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
    private boolean datenbankFehlerStatus;
    private boolean spsFehlerStatus;
    public ExceptionEventHandlerScope(){
        sessions=new ConcurrentSkipListSet<>();
        this.datenbankFehlerStatus=false;
        this.spsFehlerStatus=false;
    };


    public void add(ExceptionWebSocket session){
        if ( session!=null) this.sessions.add(session);
        else System.out.println("session null");
        System.out.println(this.sessions.contains(session));
    }
    public void delete(ExceptionWebSocket session){
        if ( session!=null) this.sessions.remove(session);
    }
    
    /*public void send(@Observes @DB_Exception Exception ex) {
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
    }*/
    public void send() {
        ExceptionWebSocket s[]=this.sessions.toArray(new ExceptionWebSocket[this.sessions.size()]);
        
        for (ExceptionWebSocket item : s) {
            System.out.println(item.toString());
            if (item.getSession() == null) {
                sessions.remove(item);
            } else {
                item.send(this.toJson());  
            }
        }
    }
    
    public void spsFehlerStatus(Boolean wert){
        if(wert.equals(this.spsFehlerStatus)){   
        }else{
            this.spsFehlerStatus=wert;
            this.send();
        }
    }
    public void datenbankFehlerStatus(Boolean wert){
        if(wert.equals(this.datenbankFehlerStatus)){   
        }else{
            this.datenbankFehlerStatus=wert;
            this.send();
        }
    }
    public String toJson(){
        String json = new String();
        json += '{';
        json += "\"datenbankFehlerStatus\": " + datenbankFehlerStatus + ",";
        json += "\"spsFehlerStatus\": \"" + spsFehlerStatus + "\"";
        json+='}';
        return json;
    }
}
