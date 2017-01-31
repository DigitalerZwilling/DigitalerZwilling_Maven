/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.DatenKlassen;

import java.time.LocalDateTime;

/**
 *
 * @author user
 */
public class ServerSystem extends Element
{
    private boolean datenbankFehlerStatus;
    private boolean spsFehlerStatus;

    public ServerSystem(Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.datenbankFehlerStatus=false;
        this.spsFehlerStatus=false;
    }

    public boolean isDatenbankFehlerStatus() {
        return datenbankFehlerStatus;
    }

    public void setDatenbankFehlerStatus(boolean datenbankFehlerStatus) {
        this.datenbankFehlerStatus = datenbankFehlerStatus;
    }

    public boolean isSpsFehlerStatus() {
        return spsFehlerStatus;
    }

    public void setSpsFehlerStatus(boolean spsFehlerStatus) {
        this.spsFehlerStatus = spsFehlerStatus;
    }
    

    @Override
    public String toJson() {
        String json = new String();
        json += '{';
        json += "\"datenbankFehlerStatus\": \"" + datenbankFehlerStatus + "\",";
        json += "\"spsFehlerStatus\": \"" + spsFehlerStatus + "\"";
        json+='}';
        return json;
    }
    
}
