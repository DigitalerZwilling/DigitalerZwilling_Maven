package de.hsos.digitalerzwilling.Cache;

import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.DatenKlassen.Element;
import de.hsos.digitalerzwilling.DatenKlassen.Sektor;
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
public class SektorCache extends Cache{
    @Inject private DatenbankSchnittstelle datenbankschnittstelle;
    
    @Override
    public void update() throws DBErrorException {
        try {
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_sektor, stoerung, zeitstempel, user_parameter FROM Sektor");
            List<String> ids = rsMap.get("ID_SEKTOR");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> stoerung = rsMap.get("STOERUNG");  //int
            
            Sektor sektor;
            for (int i=0;i<ids.size();i++){
                sektor=(Sektor)(state==true?elements[0].get(Long.parseLong(ids.get(i))):elements[1].get(Long.parseLong(ids.get(i))));
                
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                sektor.setZeitstempel(LocalDateTime.parse(ourTime));
                sektor.setStoerung(Integer.parseInt(stoerung.get(i)));
                sektor.setUser_Parameter(user_parameter.get(i));
                
                sektor.setWarentraegerIDs(this.readWarentraeger(sektor.getId()));
                sektor.setRoboterIDs(this.readRoboter(sektor.getId()));
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
            Map<Long,Element> allSektor1=new HashMap<>();
            Map<Long,Element> allSektor2=new HashMap<>();
            
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_sektor, stoerung, position_x, position_y, position_z, position_ausrichtung, bezeichnung, zeitstempel, user_parameter FROM Sektor");
            List<String> ids = rsMap.get("ID_SEKTOR");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> stoerung = rsMap.get("STOERUNG");  //int
            
            List<String> x = rsMap.get("POSITION_X");   //int
            List<String> y = rsMap.get("POSITION_Y");   //int
            List<String> z = rsMap.get("POSITION_Z");   //int
            List<String> ausrichtung = rsMap.get("POSITION_AUSRICHTUNG");   //int
            
            Sektor sektor1,sektor2;
            for (int i=0;i<ids.size();i++){
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                sektor1=new Sektor(Integer.parseInt(stoerung.get(i)),Integer.parseInt(x.get(i)),Integer.parseInt(y.get(i)),Integer.parseInt(z.get(i)),Integer.parseInt(ausrichtung.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                sektor2=new Sektor(Integer.parseInt(stoerung.get(i)),Integer.parseInt(x.get(i)),Integer.parseInt(y.get(i)),Integer.parseInt(z.get(i)),Integer.parseInt(ausrichtung.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                
                sektor1.setWarentraegerIDs(this.readWarentraeger(sektor1.getId()));
                sektor2.setWarentraegerIDs(this.readWarentraeger(sektor2.getId()));
                
                sektor1.setNachTransportbandIDs(this.readNachTransportband(sektor1.getId()));
                sektor2.setNachTransportbandIDs(this.readNachTransportband(sektor2.getId()));
                
                sektor1.setVorTransportbandIDs(this.readVorTransportband(sektor1.getId()));
                sektor2.setVorTransportbandIDs(this.readVorTransportband(sektor2.getId()));
                
                sektor1.setSensorIDs(this.readSensor(sektor1.getId()));
                sektor2.setSensorIDs(this.readSensor(sektor2.getId()));
                
                sektor1.setRoboterIDs(this.readRoboter(sektor1.getId()));
                sektor2.setRoboterIDs(this.readRoboter(sektor2.getId()));
                
                sektor1.setHubpodestIDs(this.readHubPodest(sektor1.getId()));
                sektor2.setHubpodestIDs(this.readHubPodest(sektor2.getId()));
                
                sektor1.setHubquerpodestIDs(this.readQuerHubPodest(sektor1.getId()));
                sektor2.setHubquerpodestIDs(this.readQuerHubPodest(sektor2.getId()));
                
                allSektor1.put(sektor1.getId(),(sektor1));
                allSektor2.put(sektor2.getId(),(sektor2));
            }   
            
            Map<Long,Element>[] m = new Map[2];
            m[0]=allSektor1;
            m[1]=allSektor2;
            this.setElements(m);
            
        } catch (DBNotFoundException ex) {
            Logger.getLogger(SektorCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(SektorCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        }
    }
    
    private List<Long> readWarentraeger(Long id) throws DBNotFoundException{
        List<Long> idsLong = new ArrayList<>();
        try {
            Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_warentraeger FROM Sektor_Warentraeger WHERE id_sektor="+id);
            List<String> ids = rsMap.get("ID_WARENTRAEGER");
            
            
            if(ids==null) return idsLong;
            
            for (String s : ids){
                idsLong.add(Long.parseLong(s));
            }
        } catch (QueryException ex) {
            Logger.getLogger(SektorCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idsLong;
    }
    
    private List<Long> readVorTransportband(Long id) throws DBNotFoundException, QueryException{
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_transportband FROM Transportband WHERE id_sektor_nach="+id);
        List<String> ids = rsMap.get("ID_TRANSPORTBAND");
        
        List<Long> idsLong= new ArrayList<>();
        if(ids==null) return idsLong;
        
        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
    
    private List<Long> readNachTransportband(Long id) throws DBNotFoundException, QueryException{
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_transportband FROM Transportband WHERE id_sektor_vor="+id);
        List<String> ids = rsMap.get("ID_TRANSPORTBAND");
        
        List<Long> idsLong= new ArrayList<>();
        if(ids==null) return idsLong;

        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
    
    private List<Long> readSensor(Long id) throws DBNotFoundException, QueryException{
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_sensor FROM Sensor WHERE id_sektor="+id);
        List<String> ids = rsMap.get("ID_SENSOR");
        
        List<Long> idsLong= new ArrayList<>();
        if(ids==null) return idsLong;

        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
    
    private List<Long> readHubPodest(Long id) throws DBNotFoundException, QueryException{
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_hubpodest FROM Hubpodest WHERE id_sektor="+id);
        List<String> ids = rsMap.get("ID_HUBPODEST");
        
        List<Long> idsLong= new ArrayList<>();
        if(ids==null) return idsLong;

        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
    
    private List<Long> readQuerHubPodest(Long id) throws DBNotFoundException, QueryException{
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_hubquerpodest FROM Hubquerpodest WHERE id_sektor="+id);
        List<String> ids = rsMap.get("ID_HUBQUERPODEST");
        
        List<Long> idsLong= new ArrayList<>();
        if(ids==null) return idsLong;

        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
    
    private List<Long> readRoboter(Long id) throws DBNotFoundException, QueryException{
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_roboter FROM Roboter_Sektor WHERE id_sektor="+id);
        List<String> ids = rsMap.get("ID_ROBOTER");
        
        List<Long> idsLong= new ArrayList<>();
        if(ids==null) return idsLong;

        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
}
