/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.Cache.Updater;

import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.Websockets.WebSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Timer;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


/**
 *
 * @author User
 */
@ApplicationScoped
public class Updater {
    private final List<Cache> caches;
    private final List<WebSocket> webSockets;
    
    private boolean update = true;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
    
    
    private List<WebSocket> toRegister;
    @Inject
    private WebSocketUpdateThread webSocketUpdateThread;
    private Thread webSocketThread;
    
    @Inject
    private CacheUpdateThread cacheUpdateThread;
    private Thread cacheThraed;
    
    @Resource
    private ManagedThreadFactory managedThreadFactory;
    
    Boolean websocketsGesperrt=false;
    //Boolean test=false;
    
    //@Resource
    //private TimerService timerService;
    
    @EJB
    private SelfTimer ejbTimerService;
    
    private Timer timer;
    
    public Updater(){
        caches = new ArrayList<>();
        webSockets = new ArrayList<>();
        this.toRegister=new ArrayList<>();
        //timer = timerService.createTimer(500, 500, "New Updater interval Timer");
    }
    
    Updater(int ms){
        caches = new ArrayList<>();
        webSockets = new ArrayList<>();
        //timer = timerService.createTimer(ms, ms, "New Updater interval Timer");
    }
    
    @PostConstruct
    public void init(){
        //timer = timerService.createTimer(0, 500, "New Updater interval Timer");
        //timer = timerService.createTimer(500, 500, "New Updater interval Timer");
        this.ejbTimerService.cancelTimer("New Updater interval Timer");
        
        this.ejbTimerService.createTimer(1000, 500, "New Updater interval Timer");
        //System.out.println("erstellt!!!!!!!!!!!!!!!!!!!!!");
        //timerService.crea
    }
    
    public void updateWebSockets(){
        websocketsGesperrt=true;
        //System.out.println("update websockets start");
        List<WebSocket> toDelete=new ArrayList<WebSocket>();
        for(WebSocket webSocket: webSockets){
            try{
            if(webSocket==null) toDelete.add(webSocket);
            else if(webSocket.getSession().isOpen())webSocket.update();
            else toDelete.add(webSocket);
            
            }catch (IllegalStateException ex){
                toDelete.add(webSocket);
                java.util.logging.Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
            }catch (RuntimeException ex){
                toDelete.add(webSocket);
                java.util.logging.Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(WebSocket webSocket: toDelete){
            this.webSockets.remove(webSocket);
        }
        //System.out.println("update websockets end");
        websocketsGesperrt=false;
        for(WebSocket webSocket: toRegister){
            this.webSockets.add(webSocket);
        }
        toRegister.clear();
    }
    
    public void updateCaches(){
        //test=true;
        //System.out.println("updates Caches");
        for(Cache cache: caches){
            try {
                cache.update();
            } catch (DBErrorException ex) {
                java.util.logging.Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //System.out.println("updates Caches fertig");
        //test=false;
    }
    
    //@Timeout
    public void updateAll(Timer timer){
        if(!update)
            return;
        //System.out.println(this.webSockets.size());
        for(Cache cache: caches){
            cache.toggleState();
            break;
        }
        
        if(!cacheUpdateThread.isRunning()){
            cacheThraed = managedThreadFactory.newThread(cacheUpdateThread);
            
            this.cacheUpdateThread.run();
            //cacheThraed.start();
        }
        else
            Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, "TIMEOUT: Cache update takes to long...");
        if(!webSocketUpdateThread.isRunning()){
            webSocketThread = managedThreadFactory.newThread(webSocketUpdateThread);
            this.webSocketUpdateThread.run();
            //webSocketThread.start();
        }
        else
            Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, "TIMEOUT: WebSocket update takes to long...");
    }
    
    public void registerCache(Cache cache){
        caches.add(cache);
    }
    
    public void addWebSocket(WebSocket webSocket){
        //System.out.println("adde webSocket"+webSocket.toString());
        
       
        if(!websocketsGesperrt) this.webSockets.add(webSocket);
        else this.toRegister.add(webSocket);
    }
    
    public void removeWebSocket(WebSocket webSocket){
      //System.out.println("loesche webSocket");
      //System.out.println(websocketsGesperrt);
      if(!websocketsGesperrt) this.webSockets.remove(webSocket);
    }
}
