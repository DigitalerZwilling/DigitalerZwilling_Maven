package de.hsos.digitalerzwilling.Cache;

import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.Cache.Exception.ElementNotFoundException;
import de.hsos.digitalerzwilling.DatenKlassen.Element;
import de.hsos.digitalerzwilling.DatenKlassen.Werkzeug;
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
public class WerkzeugCache extends Cache{
    @Inject private DatenbankSchnittstelle datenbankschnittstelle;
    
    @Override
    public void update() throws DBErrorException {
        try {
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_werkzeug,zeitstempel,user_parameter,zustand FROM Werkzeug");
            
            List<String> ids_w = rsMap.get("ID_WERKZEUG");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> zustand = rsMap.get("ZUSTAND");
            if(ids_w==null||zeitstempel==null||user_parameter==null||zustand==null) throw new QueryException();
            Werkzeug werkzeug;
            for (int i=0;i<ids_w.size();i++){
                werkzeug=(Werkzeug)(state==true?elements[0].get(Long.parseLong(ids_w.get(i))):elements[1].get(Long.parseLong(ids_w.get(i))));                 //andersrum als bei getById
                if (werkzeug==null) throw new ElementNotFoundException();
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                werkzeug.setZeitstempel(LocalDateTime.parse(ourTime));
                werkzeug.setUser_Parameter(user_parameter.get(i));
                werkzeug.setZustand(Integer.valueOf(zustand.get(i)));
            }
        } catch (DBNotFoundException ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        } catch (ElementNotFoundException ex) {
            Logger.getLogger(WerkzeugCache.class.getName()).log(Level.SEVERE, null, ex);
            this.updateAll();
        }
    }

    @Override
    public void updateAll() throws DBErrorException {
        try {
            Map<Long,Element> allWerkzeug1=new HashMap<>();
            Map<Long,Element> allWerkzeug2=new HashMap<>();
            
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_werkzeug,bezeichnung,zeitstempel,user_parameter,zustand FROM Werkzeug");
            List<String> ids = rsMap.get("ID_WERKZEUG");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> zustand = rsMap.get("ZUSTAND");
            
            Werkzeug werkzeug1,werkzeug2;
            for (int i=0;i<ids.size();i++){
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                werkzeug1=new Werkzeug(this.readRoboter(Long.parseLong(ids.get(i))), Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime),Integer.valueOf(zustand.get(i)));
                werkzeug2=new Werkzeug(this.readRoboter(Long.parseLong(ids.get(i))), Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime),Integer.valueOf(zustand.get(i)));
                                
                allWerkzeug1.put(werkzeug1.getId(),(werkzeug1));
                allWerkzeug2.put(werkzeug2.getId(),(werkzeug2));
            }   
            
            Map<Long,Element>[] m = new Map[2];
            m[0]=allWerkzeug1;
            m[1]=allWerkzeug2;
            this.setElements(m);
            
        } catch (DBNotFoundException ex) {
            Logger.getLogger(WerkzeugCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(WerkzeugCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        }
    }

    Long readRoboter(Long id) throws DBNotFoundException, QueryException{
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_roboter FROM Roboter_Werkzeug WHERE id_werkzeug="+id+" ");
        List<String> ids = rsMap.get("ID_ROBOTER");
        
        Long r_ids=null;
        if(ids==null) return r_ids;
        
        for (String s : ids){
            r_ids=Long.parseLong(s);
        }
        return r_ids;
    }
}
