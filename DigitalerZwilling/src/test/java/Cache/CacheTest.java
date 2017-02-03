/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.Cache.Exception.ElementNotFoundException;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.DatenKlassen.Element;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author florian
 */
public abstract class CacheTest {
    
    
    @Inject
    DatenbankSchnittstelle datenbankSchnittstelle;
    
    @Inject
    Updater updater;
    
    @Test
    abstract public void testUpdate() throws DBNotFoundException, QueryException, DBErrorException, ElementNotFoundException;
    
    @Test
    abstract public void testUpdateAll() throws ElementNotFoundException, DBNotFoundException, QueryException;
    
    @Test
    public void testGetById(){
        System.out.println("testGetByID");
        try {
            Assert.assertTrue("Element found", getCache().getById(4242L).getId()==4242);
        } catch (ElementNotFoundException ex) {
            Assert.assertTrue("Element not found", true);
        }
        try {
            getCache().getById(4250L);
            Assert.assertTrue("Not existing element found??????", false);
        } catch (ElementNotFoundException ex) {
            Assert.assertTrue("Not existing element not found", true);
        }
        
    }
    
    @Test
    public void testGetAll(){
        for(Element element : getCache().getAll()){
            
        }
    }
    
    @Test
    public void testAutoUpdate() throws InterruptedException{
        System.out.println("testAutoUpdate");
        boolean state = getCache().isState();
        /*while(state == getCache().isState())
            System.out.println("wait....");*/
        Thread.sleep(500);
        if(state != getCache().isState()){
            Assert.assertTrue("Auto-Update 0.5", state != getCache().isState());
            System.out.println("Auto-Update 0.5");
            return;
        }
        
        Thread.sleep(500);
        if(state != getCache().isState()){
            Assert.assertTrue("Auto-Update 1.0", state != getCache().isState());
            System.out.println("Auto-Update 1.0");
            return;
        }
        
        Thread.sleep(500);
        if(state != getCache().isState()){
            Assert.assertTrue("Auto-Update 1.5", state != getCache().isState());
            System.out.println("Auto-Update 1.5");
            return;
        }
        
        Thread.sleep(500);
        if(state != getCache().isState()){
            Assert.assertTrue("Auto-Update 2.0", state != getCache().isState());
            System.out.println("Auto-Update 2.0");
            return;
        }
        
        Assert.assertTrue("Auto-Update", state != getCache().isState());
        System.out.println("Auto-Update --> FAIL!!!");
    }
    
    abstract public Cache getCache();
}
