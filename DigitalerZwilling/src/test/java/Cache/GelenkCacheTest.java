/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenbankTestInsert.DatenbankTestInsert;
import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.Cache.Exception.ElementNotFoundException;
import de.hsos.digitalerzwilling.Cache.GelenkCache;
import de.hsos.digitalerzwilling.Cache.Updater.CacheUpdateThread;
import de.hsos.digitalerzwilling.Cache.Updater.SelfTimer;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.Cache.Updater.WebSocketUpdateThread;
import de.hsos.digitalerzwilling.DatenKlassen.Gelenk;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import de.hsos.digitalerzwilling.Websockets.ExceptionEventHandlerScope;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author florian
 */
@RunWith(Arquillian.class)
public class GelenkCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(GelenkCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class,ExceptionEventHandlerScope.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    GelenkCache cache;
    
    @Before
    public void setUp() throws DBNotFoundException, QueryException {
        tearDown();
        datenbankSchnittstelle.connect("./testdbConfig.cfg");
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER (ID_ROBOTER,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CacheTestRoboter1',0,0,0,0,0)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO GELENK (ID_GELENK,BEZEICHNUNG,TYP,NUMMER,GELENKSTELLUNG,ID_ROBOTER) VALUES (4242,'CacheTestGELENK1','Typ1',2,0,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO GELENK (ID_GELENK,BEZEICHNUNG,TYP,NUMMER,GELENKSTELLUNG,ID_ROBOTER) VALUES (4243,'CacheTestGELENK2','Typ2',3,90,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO GELENK (ID_GELENK,BEZEICHNUNG,TYP,NUMMER,GELENKSTELLUNG,ID_ROBOTER) VALUES (4244,'CacheTestGELENK3','Typ3',4,180,4242)");
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundException, QueryException {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("DELETE FROM GELENK WHERE ID_GELENK = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM GELENK WHERE ID_GELENK = 4243");
        datenbankTestInsert.datenbankUpdate("DELETE FROM GELENK WHERE ID_GELENK = 4244");
        datenbankTestInsert.datenbankUpdate("DELETE FROM ROBOTER WHERE ID_ROBOTER = 4242");
        datenbankTestInsert.close();
    }

    @Override
    public Cache getCache() {
        return cache; 
    }

    @Override
    public void testUpdate() throws ElementNotFoundException, DBNotFoundException, QueryException, DBErrorException {
        try {
            assertTrue("Bezeichnung",cache.getById(new Long(4242)).getBezeichnung().equalsIgnoreCase("CacheTestGELENK1"));
            assertTrue("Bezeichnung",cache.getById(new Long(4243)).getBezeichnung().equalsIgnoreCase("CacheTestGELENK2"));
            assertTrue("Bezeichnung",cache.getById(new Long(4244)).getBezeichnung().equalsIgnoreCase("CacheTestGELENK3"));
            
            assertTrue("Typ",((Gelenk)cache.getById(new Long(4242))).getTyp().equalsIgnoreCase("Typ1"));
            assertTrue("Typ",((Gelenk)cache.getById(new Long(4243))).getTyp().equalsIgnoreCase("Typ2"));
            assertTrue("Typ",((Gelenk)cache.getById(new Long(4244))).getTyp().equalsIgnoreCase("Typ3"));
            
            assertTrue("Typ",((Gelenk)cache.getById(new Long(4242))).getNummer()==2);
            assertTrue("Typ",((Gelenk)cache.getById(new Long(4243))).getNummer()==3);
            assertTrue("Typ",((Gelenk)cache.getById(new Long(4244))).getNummer()==4);
            
            assertTrue("Typ",((Gelenk)cache.getById(new Long(4242))).getGelenkstellung()==0);
            assertTrue("Typ",((Gelenk)cache.getById(new Long(4243))).getGelenkstellung()==90);
            assertTrue("Typ",((Gelenk)cache.getById(new Long(4244))).getGelenkstellung()==180);
            
            DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
            datenbankTestInsert.datenbankUpdate("UPDATE GELENK SET GELENKSTELLUNG=1 WHERE ID_GELENK=4242");
            datenbankTestInsert.datenbankUpdate("UPDATE GELENK SET GELENKSTELLUNG=91 WHERE ID_GELENK=4243");
            datenbankTestInsert.datenbankUpdate("UPDATE GELENK SET GELENKSTELLUNG=181 WHERE ID_GELENK=4244");
            datenbankTestInsert.close();
            
            Thread.sleep(500);
            Thread.sleep(500);
            Thread.sleep(500);
            Thread.sleep(500);
            Thread.sleep(500);
            Thread.sleep(500);
            //cache.update(); //Nicht gut...
            
            System.out.println(((Gelenk)cache.getById(new Long(4243))).getGelenkstellung());
            
            assertTrue("Typ",((Gelenk)cache.getById(new Long(4242))).getGelenkstellung()==1);
            assertTrue("Typ",((Gelenk)cache.getById(new Long(4243))).getGelenkstellung()==91);
            assertTrue("Typ",((Gelenk)cache.getById(new Long(4244))).getGelenkstellung()==181);
        } catch (InterruptedException ex) {
            Logger.getLogger(GelenkCacheTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void testUpdateAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
