/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.DatenKlassen;

import java.time.LocalDateTime;
import java.util.List;




/**
 *
 * @author user
 */
public class HubQuerPodest extends Element{
    
    private boolean motor;
    private boolean oben;
    private boolean mittig;
    private boolean unten;
    private List<Long> gruppenIDs;
    private Long sektorID;

    public HubQuerPodest(boolean motor, boolean oben, boolean mittig, boolean unten, Long id_Sektor, Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.motor = motor;
        this.oben = oben;
        this.mittig = mittig;
        this.unten = unten;
        this.sektorID = id_Sektor;
    }

    

    public boolean isMotor() {
        return motor;
    }

    public void setMotor(boolean motor) {
        this.motor = motor;
    }

    public boolean isOben() {
        return oben;
    }

    public void setOben(boolean oben) {
        this.oben = oben;
    }

    public boolean isMittig() {
        return mittig;
    }

    public void setMittig(boolean mittig) {
        this.mittig = mittig;
    }

    public boolean isUnten() {
        return unten;
    }

    public void setUnten(boolean unten) {
        this.unten = unten;
    }

    public List<Long> getGruppenIDs() {
        return gruppenIDs;
    }

    public void setGruppenIDs(List<Long> gruppenIDs) {
        this.gruppenIDs = gruppenIDs;
    }

    public Long getId_Sektor() {
        return sektorID;
    }

    public void setId_Sektor(Long id_Sektor) {
        this.sektorID = id_Sektor;
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
        json += "\"motor\": " + motor + ",";
        json += "\"oben\": " + oben + ",";
        json += "\"mittig\": " + mittig + ",";
        json += "\"unten\": " + unten + ",";
        
        /*json += "\"gruppenIDs\": [";
        for(int i=0;i<gruppenIDs.size();i++){
            json += gruppenIDs.get(i);
            if(i < (gruppenIDs.size()-1)){
                json += ",";
            }
        }
        json += "]";*/
        
        json += "\"sektorID\": " + sektorID;
        json += '}';
        
        return json;
    }
    
}
