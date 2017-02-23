/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.DatenKlassen;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author user
 */
public class Warentraeger extends Element{

    private int stoerung;
    private int abstand_mm;
    private int montagezustand;
    private String rFID_inhalt;
    private List<Long> artikelIDs;
    private List<Long> transportbandIDs;
    private List<Long> sektorIDs;

    public Warentraeger(int stoerung, int abstand_mm, int montagezustand, String rFID_inhalt, Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.stoerung = stoerung;
        this.abstand_mm = abstand_mm;
        this.montagezustand = montagezustand;
        this.rFID_inhalt = rFID_inhalt;
        
        this.artikelIDs=null;
        this.sektorIDs =null;
        this.transportbandIDs=null;
    }

    public int getStoerung() {
        return stoerung;
    }

    public void setStoerung(int stoerung) {
        this.stoerung = stoerung;
    }

    public int getAbstand_mm() {
        return abstand_mm;
    }

    public void setAbstand_mm(int abstand_mm) {
        this.abstand_mm = abstand_mm;
    }

    public int getMontagezustand() {
        return montagezustand;
    }

    public void setMontagezustand(int montagezustand) {
        this.montagezustand = montagezustand;
    }

    public String getrFID_inhalt() {
        return rFID_inhalt;
    }

    public void setrFID_inhalt(String rFID_inhalt) {
        this.rFID_inhalt = rFID_inhalt;
    }

    public List<Long> getArtikelIDs() {
        return artikelIDs;
    }

    public void setArtikelIDs(List<Long> artikelIDs) {
        this.artikelIDs = artikelIDs;
    }

    public List<Long> getTransportbandIDs() {
        return transportbandIDs;
    }

    public void setTransportbandIDs(List<Long> transportbandIDs) {
        this.transportbandIDs = transportbandIDs;
    }

    public List<Long> getSektorIDs() {
        return sektorIDs;
    }

    public void setSektorIDs(List<Long> sektorIDs) {
        this.sektorIDs = sektorIDs;
    }

    @Override
    public String toJson() {
        String json = new String();
        json += '{';
        json += "\"id\": " + id + ",";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",";
        json += "\"user_Parameter\": \"" + user_Parameter + "\",";
        json += "\"zeitstempel\": \"" + zeitstempel.format(DateTimeFormatter.ISO_DATE)+" "+zeitstempel.format(DateTimeFormatter.ISO_TIME) + "\",";
        json += "\"stoerung\": " + stoerung + ",";
        json += "\"abstand_mm\": " + abstand_mm + ",";
        json += "\"montagezustand\": " + montagezustand + ",";
        
        json += "\"artikelIDs\": [";
        for(int i=0;i<artikelIDs.size();i++){
            json += artikelIDs.get(i);
            if(i < (artikelIDs.size()-1)){
                json += ",";
            }
        }
        json += "],";
        
        json += "\"transportbandIDs\": [";
        for(int i=0;i<transportbandIDs.size();i++){
            json += transportbandIDs.get(i);
            if(i < (transportbandIDs.size()-1)){
                json += ",";
            }
        }
        json += "],";
        
        json += "\"sektorIDs\": [";
        for(int i=0;i<sektorIDs.size();i++){
            json += sektorIDs.get(i);
            if(i < (sektorIDs.size()-1)){
                json += ",";
            }
        }
        json += "]}";
        
        return json;
    }
    
}
