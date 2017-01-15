/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.Cache.Exception.ElementNotFoundException;
import de.hsos.digitalerzwilling.DatenKlassen.Element;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author florian
 */
public abstract class CacheTest {
    
    @Test
    abstract public void testUpdate() throws DBNotFoundException, QueryException, DBErrorException, ElementNotFoundException;
    
    @Test
    abstract public void testUpdateAll() throws DBNotFoundException, QueryException;
    
    @Test
    public void testGetById(){
        System.out.println("testGetByID");
        for(Long i=new Long(0);i<= Long.MAX_VALUE;i++){
            try {
                getCache().getById(i);
            } catch (ElementNotFoundException ex) {
                Assert.assertTrue("Elmenet not Found", true);
                return;
            }
        }
        Assert.assertTrue("Elmenet not Found", false);
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
