/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.Websockets;

import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DB_Exception;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author user
 */

@ServerEndpoint("/ExceptionWebSocket")
public class ExceptionWebSocket {
    @Inject WebSocketBean WebSocketBeanConversation;
    Session session;
    
    private Map<String,Exception> exMap;
    private Map<String,Long> timeMap;
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("open");
        exMap=new HashMap<>();
        timeMap=new HashMap<>();
        this.session=session;
        this.WebSocketBeanConversation.add(this);
        //WebSocketBeanConversation=new WebSocketBean(session);
        
    }
    /*@PostConstruct
    public void init(){
        System.out.println("post const");
        this.WebSocketBeanConversation.startConversation(session);
    }*/

    @OnMessage
    public void onMessage(String message) {
        ;
    }
    public void addToMap(Exception ex){
        String key=ex.getClass().getName()+ex.getMessage();
        Long now=new java.util.Date().getTime();
        if(exMap.containsKey(key)){
            if(this.timeMap.get(key)+(1000*60)<now){
                timeMap.put(key, now);
                this.send();
            }
        }
        else{
            exMap.put(key, ex);
            timeMap.put(key, now);
            this.send();
        }
    }
    private void send(){
        Long now=new java.util.Date().getTime();
        String ausgabe="";
        for (String s:exMap.keySet()){
            if(this.timeMap.get(s)+(1000*60)>now){
                ausgabe=ausgabe+exMap.get(s).getMessage()+"\n";
            }
        }
                try {
            session.getBasicRemote().sendText(ausgabe);
        } catch (IOException ex) {
            Logger.getLogger(ExceptionWebSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @OnClose
    public void onClose() {
        this.WebSocketBeanConversation.delete(this);
    }

    @OnError
    public void onError(Throwable t) {
        //this.WebSocketBeanConversation.endConversation();
        
        System.out.println("Error: "+t.getMessage()+" : "+ t.getClass().getName());
    }

    public Session getSession() {
        return session;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.session);
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
        final ExceptionWebSocket other = (ExceptionWebSocket) obj;
        if (!Objects.equals(this.session, other.session)) {
            return false;
        }
        return true;
    }
    
    
}
