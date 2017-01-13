/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.Websockets;

import de.hsos.digitalerzwilling.Cache.ArtikelCache;
import de.hsos.digitalerzwilling.Cache.Cache;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author user
 */
@ServerEndpoint("/ArtikelWebSocket")
public class ArtikelWebSocket extends WebSocket{
    //@Inject Updater webSocketUpdater;
    
    @Inject
    ArtikelCache artikelCache;
  
    @OnMessage
    public void messageReceiver(String message) {
        if (message.equals("LIST")){
            this.setId(null);
        }
        else{
            this.setId(Long.parseLong(message));
        }
        if (this.getRegistriert()==Boolean.FALSE){
            this.webSocketUpdater.addWebSocket(this);
        }
        this.setRegistriert(Boolean.TRUE);
    }

    @OnOpen
    public void onOpen(Session session) {
      this.setSession(session);
    }
 
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        this.setRegistriert(Boolean.FALSE);
        this.webSocketUpdater.removeWebSocket(this);
        //HIER SYSTEM OUT zur überprüfung
    }

    @Override
    protected Cache getCache() {
        return artikelCache;
    }
}
