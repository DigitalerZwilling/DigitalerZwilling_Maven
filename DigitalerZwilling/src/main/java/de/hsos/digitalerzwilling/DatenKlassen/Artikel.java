package de.hsos.digitalerzwilling.DatenKlassen;

import java.time.LocalDateTime;
import java.util.List;



/**
 *
 * @author chris
 */
public class Artikel extends Element{
    
    private List<Long> warentraegerIDs;
    
    public Artikel(Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        warentraegerIDs = null;
    }

    public List<Long> getId_Warentraeger() {
        return warentraegerIDs;
    }

    public void setId_Warentraeger(List<Long> id_Warentraeger) {
        this.warentraegerIDs = id_Warentraeger;
    }

    @Override
    public String toJson() {
        String json = new String();
        json += '{';
        json += "\"id\": " + id + ",";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",";
        json += "\"user_Parameter\": \"" + user_Parameter + "\",";
        json += "\"zeitstempel\": \"" + zeitstempel.toString() + "\",";
        
        json += "\"warentraegerIDs\": [";
        for(int i=0;i<warentraegerIDs.size();i++){
            json += warentraegerIDs.get(i);
            if(i < (warentraegerIDs.size()-1)){
                json += ",";
            }
        }
        json += "]}";
        
        return json;
    }
}
    
    
    

    