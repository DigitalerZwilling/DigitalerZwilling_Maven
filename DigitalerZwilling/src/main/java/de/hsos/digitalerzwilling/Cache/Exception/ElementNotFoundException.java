/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.Cache.Exception;

/**
 *
 * @author florian
 */
public class ElementNotFoundException extends Exception {
    public ElementNotFoundException(){
        super("ElementNotFoundException");
    }
    
    public ElementNotFoundException(String str){
        super(str);
    }
}
