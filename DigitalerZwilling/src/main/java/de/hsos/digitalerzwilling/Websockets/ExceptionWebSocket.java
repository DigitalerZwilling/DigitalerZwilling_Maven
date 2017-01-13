/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.Websockets;

import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DB_Exception;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
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
    Session session;
    @OnOpen
    public void onOpen(Session session) {
        this.session=session;
    }

    @OnMessage
    public String onMessage(String message) {
        
        return null;
    }

    public void send(@Observes @DB_Exception Exception ex){
        try {
            this.session.getBasicRemote().sendText(ex.getMessage());
            System.out.println("1");
        } catch (IOException ex1) {
            Logger.getLogger(ExceptionWebSocket.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }
    
}
