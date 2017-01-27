/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.Websockets;

import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.Cache.Exception.ElementNotFoundException;
import de.hsos.digitalerzwilling.Cache.SystemCache;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DB_Exception;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ExceptionWebSocket extends WebSocket{
    @Inject private SystemCache cache;

    
    @OnOpen
    public void onOpen(Session session) {
        this.setId(0l);
        this.setSession(session);
        if (this.getRegistriert()==Boolean.FALSE){
          this.webSocketUpdater.addWebSocket(this);
        }
        this.setRegistriert(Boolean.TRUE);
        
    }

    /*@PostConstruct
    public void init(){
        System.out.println("post const");
        this.WebSocketBeanConversation.startConversation(session);
    }*/

    @OnMessage
    public void onMessage(String message) {
        try {
           this.send(cache.getById(0l).toJson());//send();
        } catch (ElementNotFoundException ex) {
            Logger.getLogger(ExceptionWebSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    public void addToMap(Exception ex){
        String key=ex.getClass().getName()+ex.getMessage();
        Long now=new java.util.Date().getTime();
        if(exMap.containsKey(key)){
            if(this.timeMap.get(key)+(1000*60)<now){
                timeMap.put(key, now);
                this.send();
            }else{
                return;
            }
        }
        else{
            exMap.put(key, ex);
            timeMap.put(key, now);
            this.send();
        }
    }
    */
    /*private void send(){
        Long now=new java.util.Date().getTime();
        String ausgabe="";
        for (String s:exMap.keySet()){
            if(this.timeMap.get(s)+(1000*60)>now){
                ausgabe=ausgabe+exMap.get(s).getMessage()+"\n";
            }else{
                this.timeMap.remove(s);
                this.exMap.remove(s);
            }
        }
        try {
            if (ausgabe.equals("")){
            } else {
                session.getBasicRemote().sendText(ausgabe);
            }
        } catch (IOException ex) {
            Logger.getLogger(ExceptionWebSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public void send(String Status){
        try {
            this.getSession().getBasicRemote().sendText(Status);
        } catch (IOException ex) {
            Logger.getLogger(ExceptionWebSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @OnClose
    public void onClose() {
        this.setRegistriert(Boolean.FALSE);
        this.webSocketUpdater.removeWebSocket(this);
    }

    @OnError
    public void onError(Throwable t) {
        //this.WebSocketBeanConversation.endConversation();
        
        System.out.println("Error: "+t.getMessage()+" : "+ t.getClass().getName());
    }

    
    

    
/*
    @Override
    public int compareTo(ExceptionWebSocket o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return Integer.parseInt(String.valueOf(this.createtTime-o.createtTime));
    }
*/
    @Override
    protected Cache getCache() {
        return this.cache;
    }
    
    
}
