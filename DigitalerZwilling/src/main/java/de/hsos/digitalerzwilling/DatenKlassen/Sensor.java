/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.DatenKlassen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/**
 *
 * @author user
 */
public class Sensor extends Element{

    private  int stoerung;
    private  String phy_adresse;
    private  boolean zustand;
    private Long sektorID;

    public Sensor(int stoerung, String phy_adresse, boolean zustand, Long sektorID, Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.stoerung = stoerung;
        this.phy_adresse = phy_adresse;
        this.zustand = zustand;
        this.sektorID = sektorID;
    }

    public int getStoerung() {
        return stoerung;
    }

    public void setStoerung(int stoerung) {
        this.stoerung = stoerung;
    }

    public String getPhy_adresse() {
        return phy_adresse;
    }

    public void setPhy_adresse(String phy_adresse) {
        this.phy_adresse = phy_adresse;
    }

    public boolean isZustand() {
        return zustand;
    }

    public void setZustand(boolean zustand) {
        this.zustand = zustand;
    }

    public Long getSektorID() {
        return sektorID;
    }

    public void setSektorID(Long sektorID) {
        this.sektorID = sektorID;
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
        json += "\"phy_adresse\": \"" + phy_adresse + "\",";
        json += "\"zustand\": " + zustand + ",";
        json += "\"sektorID\": " + sektorID;
        json += '}';
        return json;
    }
       
}
