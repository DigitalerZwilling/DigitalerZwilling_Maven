/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.Cache;

import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.Cache.Exception.ElementNotFoundException;
import de.hsos.digitalerzwilling.DatenKlassen.Artikel;
import de.hsos.digitalerzwilling.DatenKlassen.Element;
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
public class ArtikelCache extends Cache{
    
    
    
    @Override
    public void update() throws DBErrorException{
        try {
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_artikel,zeitstempel,user_parameter from Artikel");
            List<String> ids = rsMap.get("ID_ARTIKEL");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            Artikel artikel;
            if(ids==null||zeitstempel==null||user_parameter==null) throw new QueryException();
            for (int i=0;i<ids.size();i++){
                artikel=(Artikel)(state==true?elements[0].get(Long.parseLong(ids.get(i))):elements[1].get(Long.parseLong(ids.get(i))));                 //andersrum als bei getById
                if (artikel==null) throw new ElementNotFoundException();
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                artikel.setZeitstempel(LocalDateTime.parse(ourTime));
                artikel.setUser_Parameter(user_parameter.get(i));
                artikel.setId_Warentraeger(this.readWarentraeger(artikel.getId()));
            }
        } catch (DBNotFoundException ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        } catch (ElementNotFoundException ex) {
            //Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            this.updateAll();
        }
    }

    @Override
    public void updateAll() throws DBErrorException{
        try {
            Map<Long,Element> allArtikel1=new HashMap<>();
            Map<Long,Element> allArtikel2=new HashMap<>();
            
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_artikel,bezeichnung,zeitstempel,user_parameter from Artikel");
            
            List<String> ids = rsMap.get("ID_ARTIKEL");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            
            Artikel artikel1,artikel2;            
            for (int i=0;i<ids.size();i++){
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                
                artikel1=new Artikel(Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                artikel2=new Artikel(Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                
                artikel1.setId_Warentraeger(this.readWarentraeger(artikel1.getId()));
                artikel2.setId_Warentraeger(this.readWarentraeger(artikel2.getId()));
                
                allArtikel1.put(artikel1.getId(),(artikel1));
                allArtikel2.put(artikel2.getId(),(artikel2));
            }   
            
            Map<Long,Element>[] m = new Map[2];
            m[0]=allArtikel1;
            m[1]=allArtikel2;
            this.setElements(m);
            
        } catch (DBNotFoundException ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("DB not found");
        } catch (QueryException ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorException("Query error");
        }
    }


    private List<Long> readWarentraeger(Long id) throws DBNotFoundException{
        List<Long> w_ids= new ArrayList<>();
        try {
            Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_warentraeger from Artikel_Warentraeger where id_artikel="+id);
            List<String> ids = rsMap.get("ID_WARENTRAEGER");
            
            
            if(ids==null) return w_ids;
            for (String s : ids){
                w_ids.add(Long.parseLong(s));
            }
            
        } catch (QueryException ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w_ids;
    }
}
