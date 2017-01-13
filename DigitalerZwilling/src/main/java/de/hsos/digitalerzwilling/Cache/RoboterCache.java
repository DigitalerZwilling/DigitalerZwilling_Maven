package de.hsos.digitalerzwilling.Cache;

import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.DatenKlassen.Element;
import de.hsos.digitalerzwilling.DatenKlassen.Roboter;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author User
 */
@ApplicationScoped
public class RoboterCache extends Cache{
    @Inject private DatenbankSchnittstelle datenbankschnittstelle;
    
    @Override
    public void update() throws DBErrorException {

        try {
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_roboter,stoerung,position_x,position_y,position_z,position_ausrichtung,zeitstempel,user_parameter FROM Roboter");
            
            List<String> ids = rsMap.get("ID_ROBOTER");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> stoerung = rsMap.get("STOERUNG");  //int
            
            List<String> x = rsMap.get("POSITION_X");   //int
            List<String> y = rsMap.get("POSITION_Y");   //int
            List<String> z = rsMap.get("POSITION_Z");   //int
            List<String> ausrichtung = rsMap.get("POSITION_AUSRICHTUNG");   //int
            
            Roboter roboter;
            for (int i=0;i<ids.size();i++){
                roboter=(Roboter)(state==true?elements[0].get(Long.parseLong(ids.get(i))):elements[1].get(Long.parseLong(ids.get(i))));
                
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                roboter.setZeitstempel(LocalDateTime.parse(ourTime));
                roboter.setStoerung(Integer.parseInt(stoerung.get(i)));
                roboter.setUser_Parameter(user_parameter.get(i));
                
                roboter.setAusrichtung(Integer.parseInt(ausrichtung.get(i)));
                roboter.setX(Integer.parseInt(x.get(i)));
                roboter.setY(Integer.parseInt(y.get(i)));
                roboter.setZ(Integer.parseInt(z.get(i)));
                
                roboter.setId_Gelenke(this.readGelenke(roboter.getId()));
                roboter.setId_Sektor(this.readSektor(roboter.getId()));
                roboter.setId_Werkzeug(this.readWerkzeug(roboter.getId()));
            }
        } catch (DBNotFoundException ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        }
    }

    @Override
    public void updateAll() throws DBErrorException {
        try {
            Map<Long,Element> allRoboter1=new HashMap<>();
            Map<Long,Element> allRoboter2=new HashMap<>();
            
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_roboter, bezeichnung, stoerung, position_x, position_y, position_z, position_ausrichtung, zeitstempel, user_parameter FROM Roboter");
            List<String> ids = rsMap.get("ID_ROBOTER");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> stoerung = rsMap.get("STOERUNG");  //int
            
            List<String> x = rsMap.get("POSITION_X");   //int
            List<String> y = rsMap.get("POSITION_Y");   //int
            List<String> z = rsMap.get("POSITION_Z");   //int
            List<String> ausrichtung = rsMap.get("POSITION_AUSRICHTUNG");   //int
            
            Roboter roboter1,roboter2;
            for (int i=0;i<ids.size();i++){
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                roboter1=new Roboter(Integer.parseInt(stoerung.get(i)),Integer.parseInt(x.get(i)),Integer.parseInt(y.get(i)),Integer.parseInt(z.get(i)),Integer.parseInt(ausrichtung.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                roboter2=new Roboter(Integer.parseInt(stoerung.get(i)),Integer.parseInt(x.get(i)),Integer.parseInt(y.get(i)),Integer.parseInt(z.get(i)),Integer.parseInt(ausrichtung.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                
                roboter1.setId_Gelenke(this.readGelenke(roboter1.getId()));
                roboter2.setId_Gelenke(this.readGelenke(roboter2.getId()));
                
                roboter1.setId_Sektor(this.readSektor(roboter1.getId()));
                roboter2.setId_Sektor(this.readSektor(roboter2.getId()));
                
                roboter1.setId_Werkzeug(this.readWerkzeug(roboter1.getId()));
                roboter2.setId_Werkzeug(this.readWerkzeug(roboter2.getId()));
                
                allRoboter1.put(roboter1.getId(),(roboter1));
                allRoboter2.put(roboter2.getId(),(roboter2));
            }   
            
            Map<Long,Element>[] m = new Map[2];
            m[0]=allRoboter1;
            m[1]=allRoboter2;
            this.setElements(m);
            
        } catch (DBNotFoundException ex) {
            Logger.getLogger(RoboterCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(RoboterCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        }
    }
    
    List<Long> readSektor(Long id) throws DBNotFoundException{
        List<Long> s_ids= new ArrayList<>();
        try {
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_sektor FROM Roboter_Sektor WHERE id_roboter="+id);
            List<String> ids = rsMap.get("ID_SEKTOR");
            
            
            if(ids==null) return s_ids;
            
            for (String s : ids){
                s_ids.add(Long.parseLong(s));
            }
            
        } catch (QueryException ex) {
            Logger.getLogger(RoboterCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s_ids;
    }
    List<Long> readGelenke(Long id) throws DBNotFoundException{
        List<Long> g_ids= new ArrayList<>();
        try {
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_gelenk FROM Gelenk WHERE id_roboter="+id);
            List<String> ids = rsMap.get("ID_GELENK");
            
            
            if(ids==null) return g_ids;
            
            for (String s : ids){
                g_ids.add(Long.parseLong(s));
            }
            
        } catch (QueryException ex) {
            Logger.getLogger(RoboterCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g_ids;
    }
    List<Long> readWerkzeug(Long id) throws DBNotFoundException{
        List<Long> werk_ids= new ArrayList<>();
        try {
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_werkzeug FROM Roboter_Werkzeug WHERE id_roboter="+id);
            List<String> ids = rsMap.get("ID_WERKZEUG");
            
            
            if(ids==null) return werk_ids;
            
            for (String s : ids){
                werk_ids.add(Long.parseLong(s));
            }
            
        } catch (QueryException ex) {
            Logger.getLogger(RoboterCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return werk_ids;
    }
}
