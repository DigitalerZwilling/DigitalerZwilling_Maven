package de.hsos.digitalerzwilling.Cache;

import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.Cache.Exception.ElementNotFoundException;
import de.hsos.digitalerzwilling.DatenKlassen.Element;
import de.hsos.digitalerzwilling.DatenKlassen.HubPodest;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import java.time.LocalDateTime;
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
public class HubPodestCache extends Cache{
    
    
    @Override
    public void update() throws DBErrorException {
        try {
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_hubpodest,oben,unten,zeitstempel,user_parameter from hubpodest");
            List<String> ids = rsMap.get("ID_HUBPODEST");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> oben = rsMap.get("OBEN");
            List<String> unten = rsMap.get("UNTEN");
            if(ids==null||zeitstempel==null||user_parameter==null||oben==null||unten==null) throw new QueryException();
            HubPodest hubpodest;
            for (int i=0;i<ids.size();i++){
                hubpodest=(HubPodest)(state==true?elements[0].get(Long.parseLong(ids.get(i))):elements[1].get(Long.parseLong(ids.get(i))));
                if (hubpodest==null) throw new ElementNotFoundException();
                String outTime=zeitstempel.get(i).replace(' ', 'T');
                hubpodest.setZeitstempel(LocalDateTime.parse(outTime));
                
                hubpodest.setOben(Long.parseLong(oben.get(i))!=0);
                hubpodest.setUnten(Long.parseLong(unten.get(i))!=0);
                hubpodest.setUser_Parameter(user_parameter.get(i));
            }
        } catch (DBNotFoundException ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        } catch (ElementNotFoundException ex) {
            Logger.getLogger(HubPodestCache.class.getName()).log(Level.SEVERE, null, ex);
            this.updateAll();
        }
    }


    @Override
    public void updateAll() throws DBErrorException {
        try {
            Map<Long,Element> allHuPo1=new HashMap<>();
            Map<Long,Element> allHuPo2=new HashMap<>();
            
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_hubpodest,bezeichnung,oben,unten,id_sektor,zeitstempel,user_parameter from hubpodest");
            List<String> ids = rsMap.get("ID_HUBPODEST");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> oben = rsMap.get("OBEN");
            List<String> unten = rsMap.get("UNTEN");
            List<String> sektor = rsMap.get("ID_SEKTOR");
            
            HubPodest hupo1,hupo2;
            for (int i=0;i<ids.size();i++){
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                hupo1=new HubPodest((Long.parseLong(oben.get(i))!=0),(Long.parseLong(unten.get(i))!=0),Long.parseLong(sektor.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                hupo2=new HubPodest((Long.parseLong(oben.get(i))!=0), (Long.parseLong(unten.get(i))!=0),Long.parseLong(sektor.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                
                allHuPo1.put(hupo1.getId(),(hupo1));
                allHuPo2.put(hupo2.getId(),(hupo2));
            }   
            
            Map<Long,Element>[] m = new Map[2];
            m[0]=allHuPo1;
            m[1]=allHuPo2;
            this.setElements(m);
            
        } catch (DBNotFoundException ex) {
            Logger.getLogger(HubPodestCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(HubPodestCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        }
    }
}
