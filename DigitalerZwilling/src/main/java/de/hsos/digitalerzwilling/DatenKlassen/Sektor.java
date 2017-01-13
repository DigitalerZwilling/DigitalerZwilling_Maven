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
public class Sektor extends Element{

    private int stoerung;
    private int x;
    private int y;
    private int z;
    private int ausrichtung;
    private List<Long> warentraegerIDs;
    private List<Long> hubpodestIDs;
    private List<Long> hubquerpodestIDs;
    private List<Long> roboterIDs;
    private List<Long> sensorIDs;
    private List<Long> vorTransportbandIDs;
    private List<Long> nachTransportbandIDs;

    public Sektor(int stoerung, int x, int y, int z, int ausrichtung,  Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.stoerung = stoerung;
        this.x = x;
        this.y = y;
        this.z = z;
        this.ausrichtung = ausrichtung;
        
    }

    

    public int getStoerung() {
        return stoerung;
    }

    public void setStoerung(int stoerung) {
        this.stoerung = stoerung;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getAusrichtung() {
        return ausrichtung;
    }

    public void setAusrichtung(int ausrichtung) {
        this.ausrichtung = ausrichtung;
    }

    public List<Long> getWarentraegerIDs() {
        return warentraegerIDs;
    }

    public void setWarentraegerIDs(List<Long> warentraegerIDs) {
        this.warentraegerIDs = warentraegerIDs;
    }

    public List<Long> getHubpodestIDs() {
        return hubpodestIDs;
    }

    public void setHubpodestIDs(List<Long> hubpodestIDs) {
        this.hubpodestIDs = hubpodestIDs;
    }

    public List<Long> getHubquerpodestIDs() {
        return hubquerpodestIDs;
    }

    public void setHubquerpodestIDs(List<Long> hubquerpodestIDs) {
        this.hubquerpodestIDs = hubquerpodestIDs;
    }

    public List<Long> getRoboterIDs() {
        return roboterIDs;
    }

    public void setRoboterIDs(List<Long> roboterIDs) {
        this.roboterIDs = roboterIDs;
    }

    public List<Long> getSensorIDs() {
        return sensorIDs;
    }

    public void setSensorIDs(List<Long> sensorIDs) {
        this.sensorIDs = sensorIDs;
    }

    public List<Long> getVorTransportbandIDs() {
        return vorTransportbandIDs;
    }

    public void setVorTransportbandIDs(List<Long> vorTransportbandIDs) {
        this.vorTransportbandIDs = vorTransportbandIDs;
    }

    public List<Long> getNachTransportbandIDs() {
        return nachTransportbandIDs;
    }

    public void setNachTransportbandIDs(List<Long> nachTransportbandIDs) {
        this.nachTransportbandIDs = nachTransportbandIDs;
    }

    @Override
    public String toJson() {
    String json = new String();
        json += '{';
        json += "\"id\": " + id + ",";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",";
        json += "\"user_Parameter\": \"" + user_Parameter + "\",";
        json += "\"zeitstempel\": \"" + zeitstempel.toString() + "\",";
        json += "\"stoerung\": " + stoerung + ",";
        json += "\"x\": " + x + ",";
        json += "\"y\": " + y + ",";
        json += "\"z\": " + z + ",";
        json += "\"ausrichtung\": " + ausrichtung + ",";
        
        json += "\"warentraegerIDs\": [";
        for(int i=0;i<warentraegerIDs.size();i++){
            json += warentraegerIDs.get(i);
            if(i < (warentraegerIDs.size()-1)){
                json += ",";
            }
        }
        json += "],";
        
        json += "\"hubpodestIDs\": [";
        for(int i=0;i<hubpodestIDs.size();i++){
            json += hubpodestIDs.get(i);
            if(i < (hubpodestIDs.size()-1)){
                json += ",";
            } 
        }
        json += "],";
        
        json += "\"hubquerpodestIDs\": [";
        for(int i=0;i<hubquerpodestIDs.size();i++){
            json += hubquerpodestIDs.get(i);
            if(i < (hubquerpodestIDs.size()-1)){
                json += ",";
            }
        }
        json += "],";
        
        json += "\"roboterIDs\": [";
        for(int i=0;i<roboterIDs.size();i++){
            json += roboterIDs.get(i);
            if(i < (roboterIDs.size()-1)){
                json += ",";
            }
        }
        json += "],";
        
        json += "\"sensorIDs\": [";
        for(int i=0;i<sensorIDs.size();i++){
            json += sensorIDs.get(i);
            if(i < (sensorIDs.size()-1)){
                json += ",";
            }
        }
        json += "],";
        
        json += "\"vorTransportbandIDs\": [";
        for(int i=0;i<vorTransportbandIDs.size();i++){
            json += vorTransportbandIDs.get(i);
            if(i < (vorTransportbandIDs.size()-1)){
                json += ",";
            }
        }
        json += "],";
        
        json += "\"nachTransportbandIDs\": [";
        for(int i=0;i<nachTransportbandIDs.size();i++){
            json += nachTransportbandIDs.get(i);
            if(i < (nachTransportbandIDs.size()-1)){
                json += ",";
            }
        }
        json += "]}";
        
        return json;
    }
        
}
