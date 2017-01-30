/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.Cache;

import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import de.hsos.digitalerzwilling.DatenKlassen.ServerSystem;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author user
 */
@ApplicationScoped
public class SystemCache extends Cache{
    
    @Inject
    DatenbankSchnittstelle datenbankSchnittstelle;
    
    final private Long SPS_Heartbeat_Deadline_ms=1000*60*1l;        //Timeout eine Minute
    private LocalDateTime lastTime=null;
    private Long lastTimeInServerTimeMS=0l;

    @Override
    public void update() throws DBErrorException {
        ServerSystem system = (ServerSystem) (state==true?elements[0].get(0l):elements[1].get(0l));
        try {
            Map<String,List<String>> rsMap = datenbankSchnittstelle.datenbankAnfrage("SELECT ZEITSTEMPEL FROM HEARTBEAT LIMIT 1");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            if(zeitstempel.size()>0 && lastTime!=null){
                Long now=new java.util.Date().getTime();
                LocalDateTime readTime=LocalDateTime.parse(zeitstempel.get(0).replace(' ', 'T'));
                if(readTime.isAfter(lastTime)){
                    this.lastTime=readTime;
                    this.lastTimeInServerTimeMS=new java.util.Date().getTime();
                    system.setSpsFehlerStatus(false);
                }else{
                    if(now-this.lastTimeInServerTimeMS>this.SPS_Heartbeat_Deadline_ms){
                        system.setSpsFehlerStatus(true);      
                    }
                    else{
                        system.setSpsFehlerStatus(false);
                    }
                }
                
                system.setDatenbankFehlerStatus(false);
                
            }
        } catch (DBNotFoundException ex) {
            system.setDatenbankFehlerStatus(true);
            Logger.getLogger(SystemCache.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryException ex) {
            Logger.getLogger(SystemCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateAll() throws DBErrorException {
        try {
            elements[0] = new HashMap<>();
            elements[1] = new HashMap<>();
            
            Map<String,List<String>> rsMap = datenbankSchnittstelle.datenbankAnfrage("SELECT ZEITSTEMPEL FROM HEARTBEAT LIMIT 1");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
           
            
            if(zeitstempel.size()>0){

                LocalDateTime readTime=LocalDateTime.parse(zeitstempel.get(0).replace(' ', 'T'));
                elements[0].put(0l, new ServerSystem(0l,"SYSTEM","",readTime));
                elements[1].put(0l, new ServerSystem(0l,"SYSTEM","",readTime));
                this.lastTime=readTime;
                this.lastTimeInServerTimeMS=new java.util.Date().getTime();
            }
            else{
                throw new QueryException("kein Zeitstempel in Heartbeat");//Update Failed
            }
        } catch (DBNotFoundException ex) {
            elements[0].put(0l, new ServerSystem(0l,"SYSTEM","",null));
            elements[1].put(0l, new ServerSystem(0l,"SYSTEM","",null));
            Logger.getLogger(SensorCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(SensorCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        }
    }
    
}