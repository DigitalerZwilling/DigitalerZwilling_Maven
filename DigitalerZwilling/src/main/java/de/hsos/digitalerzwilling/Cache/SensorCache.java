package de.hsos.digitalerzwilling.Cache;

import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.DatenKlassen.Sensor;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
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
 * @author User
 */
@ApplicationScoped
public class SensorCache extends Cache{
    
    @Inject
    DatenbankSchnittstelle datenbankSchnittstelle;

    @Override
    public void update() throws DBErrorException {
        try {
            Map<String,List<String>> rsMap = this.datenbankSchnittstelle.datenbankAnfrage("SELECT id_sensor,stoerung,zustand,user_parameter,zeitstempel from Sensor2");
            
            List<String> id = rsMap.get("ID_SENSOR");
            List<String> stoerung = rsMap.get("STOERUNG");
            List<String> zustand = rsMap.get("ZUSTAND");
            List<String> userParameter = rsMap.get("USER_PARAMETER");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            
            for(int i=0;i<id.size();i++){
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                Sensor sensor = (Sensor) (state==true?elements[0].get(Long.parseLong(id.get(i))):elements[0].get(Long.parseLong(id.get(i))));
                sensor.setStoerung((int)Long.parseLong(stoerung.get(i)));
                sensor.setUser_Parameter(userParameter.get(i));
                sensor.setZeitstempel(LocalDateTime.parse(ourTime));
                sensor.setZustand(Long.parseLong(zustand.get(i))!=0);
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
            elements[0] = new HashMap<>();
            elements[1] = new HashMap<>();
            
            Map<String,List<String>> rsMap = this.datenbankSchnittstelle.datenbankAnfrage("SELECT id_sensor"+
                    ",stoerung,zustand,user_parameter,zeitstempel,bezeichnung,id_sektor,phy_adresse from Sensor");
            
            List<String> id = rsMap.get("ID_SENSOR");
            List<String> stoerung = rsMap.get("STOERUNG");
            List<String> zustand = rsMap.get("ZUSTAND");
            List<String> userParameter = rsMap.get("USER_PARAMETER");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            List<String> idSektor = rsMap.get("ID_SEKTOR");
            List<String> phyAdress = rsMap.get("PHY_ADRESSE");
            
            for(int i=0;i<id.size();i++){
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                elements[0].put(Long.parseLong(id.get(i)), new Sensor((int)Long.parseLong(stoerung.get(i)), phyAdress.get(i), Long.parseLong(zustand.get(i))!=0, Long.parseLong(idSektor.get(i)), Long.parseLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalDateTime.parse(ourTime)));
                elements[1].put(Long.parseLong(id.get(i)), new Sensor((int)Long.parseLong(stoerung.get(i)), phyAdress.get(i), Long.parseLong(zustand.get(i))!=0, Long.parseLong(idSektor.get(i)), Long.parseLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalDateTime.parse(ourTime)));
            }
        } catch (DBNotFoundException ex) {
            Logger.getLogger(SensorCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(SensorCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        }
    }
}
