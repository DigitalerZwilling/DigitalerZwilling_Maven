/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception;

/**
 *
 * @author florian
 */
public class DBNotFoundException extends Exception{
    public DBNotFoundException(){
        super("DBNotFoundException");
    }
    
    public DBNotFoundException(String str){
        super(str);
    }
}
