
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
public class Werkzeug extends Element{
    int zustand;
    Long roboterID;
    
    public Werkzeug(Long roboterID, Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel, int zustand) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.zustand = zustand;
        this.roboterID = roboterID;
    }

    public int getZustand() {
        return zustand;
    }

    public void setZustand(int zustand) {
        this.zustand = zustand;
    }

    public Long getRoboterID() {
        return roboterID;
    }

    public void setRoboterID(Long roboterID) {
        this.roboterID = roboterID;
    }

    @Override
    public String toJson() {
        String json = new String();
        json += '{';
        json += "\"id\": " + id + ",";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",";
        json += "\"user_Parameter\": \"" + user_Parameter + "\",";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",\n";
        json += "\"zeitstempel\": \"" + zeitstempel.toString() + "\",";
        json += "\"zustand\": " + zustand + ",";
        json += "\"roboterID\": " + roboterID;
        json += '}';
        
        return json;
    }
    
}
