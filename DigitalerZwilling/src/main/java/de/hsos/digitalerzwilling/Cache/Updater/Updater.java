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
//import org.jboss.logging.Logger;


/**
 *
 * @author User
 */
@ApplicationScoped
public class Updater {
    private final List<Cache> caches;
    private final List<WebSocket> webSockets;
    
    
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
        System.out.println("erstellt!!!!!!!!!!!!!!!!!!!!!");
        //timerService.crea
    }
    
    public void updateWebSockets(){
        websocketsGesperrt=true;
        System.out.println("update start");
        List<WebSocket> toDelete=new ArrayList<WebSocket>();
        for(WebSocket webSocket: webSockets){
            try{
            if(webSocket==null) toDelete.add(webSocket);
            else webSocket.update();
            }catch (IllegalStateException ex){
            toDelete.add(webSocket);
            java.util.logging.Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(WebSocket webSocket: toDelete){
            this.webSockets.remove(webSocket);
        }
        System.out.println("update end");
        websocketsGesperrt=false;
        for(WebSocket webSocket: toRegister){
            this.webSockets.add(webSocket);
        }
        toRegister.clear();
    }
    
    public void updateCaches(){
        //test=true;
        for(Cache cache: caches){
            try {
                cache.update();
            } catch (DBErrorException ex) {
                java.util.logging.Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //test=false;
    }
    
    //@Timeout
    public void updateAll(Timer timer){
        System.out.println(this.webSockets.size());
        for(Cache cache: caches){
            cache.toggleState();
            break;
        }
        
        if(!cacheUpdateThread.isRunning()){
            cacheThraed = managedThreadFactory.newThread(cacheUpdateThread);
            this.cacheUpdateThread.run();
        }
        else
            Logger.getLogger("TIMEOUT: Cache update takes to long...");
        if(!webSocketUpdateThread.isRunning()){
            webSocketThread = managedThreadFactory.newThread(webSocketUpdateThread);
            this.webSocketUpdateThread.run();
        }
        else
            Logger.getLogger("TIMEOUT: WebSocket update takes to long...");
    }
    
    public void registerCache(Cache cache){
        caches.add(cache);
    }
    
    public void addWebSocket(WebSocket webSocket){
        System.out.println("adde webSocket"+webSocket.toString());
        
       
        if(!websocketsGesperrt) this.webSockets.add(webSocket);
        else this.toRegister.add(webSocket);
    }
    
    public void removeWebSocket(WebSocket webSocket){
      System.out.println("loesche webSocket");
      //System.out.println(websocketsGesperrt);
      if(!websocketsGesperrt) this.webSockets.remove(webSocket);
    }
}
