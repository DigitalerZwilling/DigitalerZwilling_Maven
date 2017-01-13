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
public abstract class Element {
    protected Long id;
    protected String bezeichnung;
    protected String user_Parameter;
    protected LocalDateTime zeitstempel;

    public Element(Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.user_Parameter = user_Parameter;
        this.zeitstempel = zeitstempel;
    }

    public Long getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public String getUser_Parameter() {
        return user_Parameter;
    }

    public LocalDateTime getZeitstempel() {
        return zeitstempel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setUser_Parameter(String user_Parameter) {
        this.user_Parameter = user_Parameter;
    }

    public void setZeitstempel(LocalDateTime zeitstempel) {
        this.zeitstempel = zeitstempel;
    }
    
    abstract public String toJson();    
    
}
