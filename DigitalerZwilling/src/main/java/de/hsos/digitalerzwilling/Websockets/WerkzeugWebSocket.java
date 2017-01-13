/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.Websockets;

import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.Cache.WerkzeugCache;
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
@ServerEndpoint("/WerkzeugWebSocket")
public class WerkzeugWebSocket extends WebSocket{
    //@Inject Updater webSocketUpdater;
    
    @Inject
    WerkzeugCache werkzeugCache;
  /**
   * 
   * zum konfigurieren der Verbindung nach Verbindungsaufbau
   * Schluesselwort "LIST" gibt an das Listen der Objekte in Json geschickt werden
   * ansonsten muss in der message die id des zu erwartenen Objektes enthalten sein
   * 
   */
  
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
    }

    @Override
    protected Cache getCache() {
        return werkzeugCache;
    }
}
