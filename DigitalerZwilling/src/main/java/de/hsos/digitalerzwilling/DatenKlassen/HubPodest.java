/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.DatenKlassen;

import java.time.LocalDateTime;



/**
 *
 * @author chris
 */
public class HubPodest extends Element{
    
    private boolean oben;
    private boolean unten;
    private Long sektorID;

    public HubPodest(boolean oben, boolean unten, Long id_sektor, Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.oben = oben;
        this.unten = unten;
        this.sektorID = id_sektor;
    }

    public boolean isOben() {
        return oben;
    }

    public void setOben(boolean oben) {
        this.oben = oben;
    }

    public boolean isUnten() {
        return unten;
    }

    public void setUnten(boolean unten) {
        this.unten = unten;
    }

    
    

    public Long getId_sektor() {
        return sektorID;
    }

    public void setId_sektor(Long id_sektor) {
        this.sektorID = id_sektor;
    }

    @Override
    public String toJson() {
        String json = new String();
        json += '{';
        json += "\"id\": " + id + ",";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",";
        json += "\"user_Parameter\": \"" + user_Parameter + "\",";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",";
        json += "\"zeitstempel\": \"" + zeitstempel.toString() + "\",";
        json += "\"oben\": " + oben + ",";
        json += "\"unten\": " + unten + ",";
        json += "\"sektorID\": " + sektorID;
        json += "}";
        return json;
    }
   
    
}