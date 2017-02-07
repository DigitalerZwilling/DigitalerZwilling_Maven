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
public class Transportband extends Element{
    
    
    private int stoerung;
    private int laenge;
    private int geschwindigkeit;
    private int reihe;
    private List<Long> warentraegerIDs;
    private Long vorSektorID;
    private Long nachSektorID;

    public Transportband(int stoerung, int laenge, int geschwindigkeit, int reihe, Long vorSektorID, Long nachSektorID, Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.stoerung = stoerung;
        this.laenge = laenge;
        this.geschwindigkeit = geschwindigkeit;
        this.reihe = reihe;
        this.vorSektorID = vorSektorID;
        this.nachSektorID = nachSektorID;
    }

    public int getReihe() {
        return reihe;
    }

    public void setReihe(int reihe) {
        this.reihe = reihe;
    }

    public int getStoerung() {
        return stoerung;
    }

    public void setStoerung(int stoerung) {
        this.stoerung = stoerung;
    }

    public int getLaenge() {
        return laenge;
    }

    public void setLaenge(int laenge) {
        this.laenge = laenge;
    }

    public int getGeschwindigkeit() {
        return geschwindigkeit;
    }

    public void setGeschwindigkeit(int geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }

    public List<Long> getWarentraegerIDs() {
        return warentraegerIDs;
    }

    public void setWarentraegerIDs(List<Long> warentraegerIDs) {
        this.warentraegerIDs = warentraegerIDs;
    }

    public Long getVorSektorID() {
        return vorSektorID;
    }

    public void setVorSektorID(Long vorSektorID) {
        this.vorSektorID = vorSektorID;
    }

    public Long getNachSektorID() {
        return nachSektorID;
    }

    public void setNachSektorID(Long nachSektorID) {
        this.nachSektorID = nachSektorID;
    }

    @Override
    public String toJson() {
        String json = new String();
        json += '{';
        json += "\"id\": " + id + ",";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",";
        json += "\"user_Parameter\": \"" + user_Parameter + "\",";
        json += "\"zeitstempel\": \"" + zeitstempel.format(DateTimeFormatter.ISO_DATE)+" "+zeitstempel.format(DateTimeFormatter.ISO_TIME) + "\",";
        json += "\"reihe\": " + reihe + ",";
        json += "\"stoerung\": " + stoerung + ",";
        json += "\"laenge\": " + laenge + ",";
        json += "\"geschwindigkeit\": " + geschwindigkeit + ",";
        
        json += "\"warentraegerIDs\": [";
        for(int i=0;i<warentraegerIDs.size();i++){
            json += warentraegerIDs.get(i);
            if(i < (warentraegerIDs.size()-1)){
                json += ",";
            }
        }
        json += "],";
        
        json += "\"vorSektorID\": " + vorSektorID + ",";
        json += "\"nachSektorID\": " + nachSektorID;
        json += '}';
        
        return json;
    }
    
}
