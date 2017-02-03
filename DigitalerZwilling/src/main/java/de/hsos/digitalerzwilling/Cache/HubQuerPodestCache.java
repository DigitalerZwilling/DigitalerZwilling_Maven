package de.hsos.digitalerzwilling.Cache;

import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.Cache.Exception.ElementNotFoundException;
import de.hsos.digitalerzwilling.DatenKlassen.HubQuerPodest;
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


/**
 *
 * @author User
 */

@ApplicationScoped
public class HubQuerPodestCache extends Cache{
    
    
    @Override
    public void update() throws DBErrorException {
        try {            
            Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_Hubquerpodest, user_parameter, motor, oben, mittig, unten, zeitstempel FROM Hubquerpodest");
            
            List<String> id = rsMap.get("ID_HUBQUERPODEST");
            List<String> userParameter = rsMap.get("USER_PARAMETER");
            List<String> motor = rsMap.get("MOTOR");
            List<String> oben = rsMap.get("OBEN");
            List<String> mittig = rsMap.get("MITTIG");
            List<String> unten = rsMap.get("UNTEN");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            if(id==null||zeitstempel==null||userParameter==null||motor==null||mittig==null||unten==null||oben==null) throw new QueryException();
            for(int i = 0; i<id.size();i++){
                HubQuerPodest huQu = (HubQuerPodest) (state==true?elements[0].get(Long.parseLong(id.get(i))):elements[1].get(Long.parseLong(id.get(i))));
                if (huQu==null) throw new ElementNotFoundException();
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                huQu.setZeitstempel(LocalDateTime.parse(ourTime));
                huQu.setUser_Parameter(userParameter.get(i));
                huQu.setMotor(Long.parseLong(motor.get(i))!=0);
                huQu.setOben(Long.parseLong(oben.get(i))!=0);
                huQu.setMittig(Long.parseLong(mittig.get(i))!=0);
                huQu.setUnten(Long.parseLong(unten.get(i))!=0);
                
            }
        } catch (DBNotFoundException ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        } catch (ElementNotFoundException ex) {
            Logger.getLogger(HubQuerPodestCache.class.getName()).log(Level.SEVERE, null, ex);
            this.updateAll();
        }
    }

    @Override
    public void updateAll() throws DBErrorException {
        try {
            elements[0] = new HashMap<>();
            elements[1] = new HashMap<>();
            
            Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_Hubquerpodest, user_parameter, motor, oben, mittig, unten, zeitstempel, bezeichnung, id_sektor FROM Hubquerpodest");
            
            List<String> id = rsMap.get("ID_HUBQUERPODEST");
            List<String> userParameter = rsMap.get("USER_PARAMETER");
            List<String> motor = rsMap.get("MOTOR");
            List<String> oben = rsMap.get("OBEN");
            List<String> mittig = rsMap.get("MITTIG");
            List<String> unten = rsMap.get("UNTEN");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> sektorId = rsMap.get("ID_SEKTOR");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            
            for(int i = 0; i<id.size();i++){
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                HubQuerPodest hubQuerPodest1 = new HubQuerPodest(Long.parseLong(motor.get(i))!=0, Long.parseLong(oben.get(i))!=0, Long.parseLong(mittig.get(i))!=0, Long.parseLong(unten.get(i))!=0, Long.parseLong(sektorId.get(i)), Long.parseLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalDateTime.parse(ourTime));
                HubQuerPodest hubQuerPodest2 = new HubQuerPodest(Long.parseLong(motor.get(i))!=0, Long.parseLong(oben.get(i))!=0, Long.parseLong(mittig.get(i))!=0, Long.parseLong(unten.get(i))!=0, Long.parseLong(sektorId.get(i)), Long.parseLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalDateTime.parse(ourTime));
                elements[0].put(Long.parseLong(id.get(i)),hubQuerPodest1);
                elements[1].put(Long.parseLong(id.get(i)), hubQuerPodest2);
                
                hubQuerPodest1.setGruppenIDs(this.readGruppenIds(hubQuerPodest1.getId()));
                hubQuerPodest1.setGruppenIDs(this.readGruppenIds(hubQuerPodest2.getId()));
            }
            
        } catch (DBNotFoundException ex) {
            Logger.getLogger(HubQuerPodestCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(HubQuerPodestCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        }
    }
    
    List<Long> readGruppenIds(Long id) throws DBNotFoundException{
        List<Long> g_ids= new ArrayList<>();
        try {
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_hubquerpodest2 FROM Hubquerpodest_Hubquerpodest WHERE id_Hubquerpodest1="+id);
            List<String> ids = rsMap.get("ID_HUBQUERPODEST2");
            
            if(ids==null) return g_ids;
            
            for (String s : ids){
                g_ids.add(Long.parseLong(s));
            }
            
        } catch (QueryException ex) {
            Logger.getLogger(RoboterCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g_ids;
    }
}
