package de.hsos.digitalerzwilling.Cache;

import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.DatenKlassen.Element;
import de.hsos.digitalerzwilling.DatenKlassen.Warentraeger;
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
public class WarentraegerCache extends Cache{
    @Inject private DatenbankSchnittstelle datenbankschnittstelle;
    
    @Override
    public void update() throws DBErrorException {
        try {
            
            Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_warentraeger, stoerung, zeitstempel, user_parameter, abstand_mm, montagezustand, RFID_inhalt FROM Warentraeger");
            
            List<String> ids = rsMap.get("ID_WARENTRAEGER");
            List<String> stoerung = rsMap.get("STOERUNG");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> abstand_mm = rsMap.get("ABSTAND_MM");
            List<String> montagezustand = rsMap.get("MONTAGEZUSTAND");
            List<String> RFID_inhalt = rsMap.get("RFID_INHALT");
            
            Warentraeger warentraeger;
            for (int i=0;i<ids.size();i++){
                warentraeger = (Warentraeger)(state==true ? elements[0].get(Long.parseLong(ids.get(i))) : elements[1].get(Long.parseLong(ids.get(i))));
                
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                warentraeger.setZeitstempel(LocalDateTime.parse(ourTime));
                warentraeger.setStoerung(Integer.parseInt(stoerung.get(i)));
                warentraeger.setUser_Parameter(user_parameter.get(i));
                warentraeger.setAbstand_mm(Integer.parseInt(abstand_mm.get(i)));
                warentraeger.setMontagezustand(Integer.parseInt(montagezustand.get(i)));
                warentraeger.setrFID_inhalt(RFID_inhalt.get(i));
                warentraeger.setTransportbandIDs(this.readTransportband(warentraeger.getId()));
                warentraeger.setArtikelIDs(this.readArtikel(warentraeger.getId()));
                warentraeger.setSektorIDs(this.readSektor(warentraeger.getId()));
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

            Map<Long,Element> allWarentraeger1=new HashMap<>();
            Map<Long,Element> allWarentraeger2=new HashMap<>();
            
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_warentraeger , bezeichnung , stoerung , zeitstempel, user_parameter, abstand_mm, RFID_inhalt,montagezustand FROM Warentraeger");
            List<String> ids = rsMap.get("ID_WARENTRAEGER");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            List<String> stoerung = rsMap.get("STOERUNG");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> abstand_mm = rsMap.get("ABSTAND_MM");
            
            List<String> rFID_inhalt = rsMap.get("RFID_INHALT");
            List<String> montagezustand = rsMap.get("MONTAGEZUSTAND");
            Warentraeger warentraeger1,warentraeger2;
            for (int i=0;i<ids.size();i++){
                
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                warentraeger1 = new Warentraeger(Integer.parseInt(stoerung.get(i)), Integer.parseInt(abstand_mm.get(i)), Integer.parseInt(montagezustand.get(i)), rFID_inhalt.get(i)+"", Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                warentraeger2 = new Warentraeger(Integer.parseInt(stoerung.get(i)), Integer.parseInt(abstand_mm.get(i)), Integer.parseInt(montagezustand.get(i)), rFID_inhalt.get(i), Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                warentraeger1.setTransportbandIDs(this.readTransportband(warentraeger1.getId()));
                warentraeger2.setTransportbandIDs(this.readTransportband(warentraeger2.getId()));
                
                warentraeger1.setSektorIDs(this.readSektor(warentraeger1.getId()));
                warentraeger2.setSektorIDs(this.readSektor(warentraeger2.getId()));
                
                warentraeger1.setArtikelIDs(this.readArtikel(warentraeger1.getId()));
                warentraeger2.setArtikelIDs(this.readArtikel(warentraeger2.getId()));
                
                allWarentraeger1.put(warentraeger1.getId(),(warentraeger1));
                allWarentraeger2.put(warentraeger2.getId(),(warentraeger2));
            }   
            
            Map<Long,Element>[] elements_local = new Map[2];
            //Map<Long,Element>[] m = new Map[2];
            elements_local[0]=allWarentraeger1;
            elements_local[1]=allWarentraeger2;
            this.setElements(elements_local);
            
        } catch (DBNotFoundException ex) {
            Logger.getLogger(WarentraegerCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(WarentraegerCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        }
    }
    
    private List<Long> readTransportband(Long id) throws DBNotFoundException{
        List<Long> idsLong= new ArrayList<>();
        try {
            Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_transportband FROM Transportband_Warentraeger WHERE id_warentraeger="+id);
            List<String> ids = rsMap.get("ID_TRANSPORTBAND");
            
            
            if(ids==null) return idsLong;
            
            for (String s : ids){
                idsLong.add(Long.parseLong(s));
            }
            
        } catch (QueryException ex) {
            Logger.getLogger(WarentraegerCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idsLong;
    }
    
    private List<Long> readSektor(Long id) throws DBNotFoundException{
        List<Long> idsLong= new ArrayList<>();
        try {
            Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_sektor FROM Sektor_Warentraeger WHERE id_warentraeger="+id);
            List<String> ids = rsMap.get("ID_SEKTOR");
            
            
            if(ids==null) return idsLong;
            
            for (String s : ids){
                idsLong.add(Long.parseLong(s));
            }
            
        } catch (QueryException ex) {
            Logger.getLogger(WarentraegerCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idsLong;
    }
    private List<Long> readArtikel(Long id) throws DBNotFoundException{
        List<Long> w_ids= new ArrayList<>();
        try {
            Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_artikel from Artikel_Warentraeger where id_warentraeger="+id);
            List<String> ids = rsMap.get("ID_ARTIKEL");
            
            
            if(ids==null) return w_ids;
            for (String s : ids){
                w_ids.add(Long.parseLong(s));
            }
            
        } catch (QueryException ex) {
            Logger.getLogger(WarentraegerCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w_ids;
    }
    
}
