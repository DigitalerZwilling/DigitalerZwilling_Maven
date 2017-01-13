/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.Cache.Updater;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author florian
 */
@ApplicationScoped
public class WebSocketUpdateThread implements Runnable{
    @Inject
    Updater updater;
    
    private boolean running;

    public boolean isRunning() {
        return running;
    }
    
    @Override
    public void run() {
        running = true;
        updater.updateWebSockets();
        running = false;
    } 
}
