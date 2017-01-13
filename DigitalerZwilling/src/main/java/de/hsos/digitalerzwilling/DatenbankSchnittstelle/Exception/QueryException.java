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
public class QueryException extends Exception{

    public QueryException() {
        super("QueryExetion");
    }
    
    public QueryException(String str){
        super(str);
    }
}
