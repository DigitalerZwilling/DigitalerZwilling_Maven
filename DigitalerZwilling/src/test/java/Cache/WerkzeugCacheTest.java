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
import de.hsos.digitalerzwilling.Cache.Updater.CacheUpdateThread;
import de.hsos.digitalerzwilling.Cache.Updater.SelfTimer;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.Cache.Updater.WebSocketUpdateThread;
import de.hsos.digitalerzwilling.Cache.WerkzeugCache;
import de.hsos.digitalerzwilling.DatenKlassen.Werkzeug;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import java.util.Objects;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author florian
 */
@RunWith(Arquillian.class)
public class WerkzeugCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(WerkzeugCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    WerkzeugCache cache;

    @Before
    public void setUp() throws DBNotFoundException, QueryException {
        tearDown();
        datenbankSchnittstelle.connect("./testdbConfig.cfg");
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER (ID_ROBOTER,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CacheTestRoboter1',0,0,0,0,0)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO WERKZEUG (ID_WERKZEUG,BEZEICHNUNG,ZUSTAND) VALUES (4242,'CacheTestWerkzeug1',1)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO WERKZEUG (ID_WERKZEUG,BEZEICHNUNG,ZUSTAND) VALUES (4243,'CacheTestWerkzeug2',1)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER_WERKZEUG (ID_ROBOTER,ID_WERKZEUG) VALUES (4242,4242)");
        
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundException, QueryException {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("DELETE FROM ROBOTER_WERKZEUG WHERE ID_WERKZEUG = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM ROBOTER WHERE ID_ROBOTER = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM WERKZEUG WHERE ID_WERKZEUG = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM WERKZEUG WHERE ID_WERKZEUG = 4243");
        datenbankTestInsert.close();
    }

    @Override
    public Cache getCache() {
        return cache; 
    }

    @Override
    public void testUpdate() throws ElementNotFoundException, DBNotFoundException, QueryException, DBErrorException {
        assertTrue("WerkzeugCachetest1", cache.getById(new Long(4242)).getBezeichnung().equalsIgnoreCase("CacheTestWerkzeug1"));
        assertTrue("WerkzeugCachetest2", cache.getById(new Long(4243)).getBezeichnung().equalsIgnoreCase("CacheTestWerkzeug2"));
        
        assertTrue("RoboterID1", Objects.equals(((Werkzeug)cache.getById(new Long(4242))).getRoboterID(), new Long(4242)));
        assertTrue("RoboterID2", ((Werkzeug)cache.getById(new Long(4243))).getRoboterID() == null);
        
        assertTrue("Zustand1", ((Werkzeug)cache.getById(new Long(4242))).getZustand() == 1);
        assertTrue("Zustand2", ((Werkzeug)cache.getById(new Long(4243))).getZustand() == 1);
        
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("UPDATE WERKZEUG SET ZUSTAND=0 WHERE ID_WERKZEUG=4242");
        datenbankTestInsert.datenbankUpdate("UPDATE WERKZEUG SET ZUSTAND=0 WHERE ID_WERKZEUG=4243");
        datenbankTestInsert.close();
        
        cache.update();
        
        assertTrue("Zustand1->Update", ((Werkzeug)cache.getById(new Long(4242))).getZustand() == 0);
        assertTrue("Zustand2->Update", ((Werkzeug)cache.getById(new Long(4242))).getZustand() == 0);
        
    }

    @Override
    public void testUpdateAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
