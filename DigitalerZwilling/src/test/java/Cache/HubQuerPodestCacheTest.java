/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenbankTestInsert.DatenbankTestInsert;
import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.Cache.Exception.ElementNotFoundException;
import de.hsos.digitalerzwilling.Cache.HubQuerPodestCache;
import de.hsos.digitalerzwilling.Cache.Updater.CacheUpdateThread;
import de.hsos.digitalerzwilling.Cache.Updater.SelfTimer;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.Cache.Updater.WebSocketUpdateThread;
import de.hsos.digitalerzwilling.DatenKlassen.HubQuerPodest;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import de.hsos.digitalerzwilling.Websockets.ExceptionEventHandlerScope;
import java.util.Objects;
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
public class HubQuerPodestCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(HubQuerPodestCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class,ExceptionEventHandlerScope.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    HubQuerPodestCache cache;
    
    @Before
    public void setUp() throws DBNotFoundException, QueryException {
        tearDown();
        datenbankSchnittstelle.connect("./testdbConfig.cfg");
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CACHETESTSEKTOR1',0,0,0,0,0)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4243,'CACHETESTSEKTOR2',0,1,4,6,90)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4244,'CACHETESTSEKTOR2',0,2,5,7,180)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBQUERPODEST (ID_HUBQUERPODEST,BEZEICHNUNG,MOTOR,OBEN,MITTIG,UNTEN,ID_SEKTOR) VALUES (4242,'CacheTestHubQuerPodest1',1,1,0,0,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBQUERPODEST (ID_HUBQUERPODEST,BEZEICHNUNG,MOTOR,OBEN,MITTIG,UNTEN,ID_SEKTOR) VALUES (4243,'CacheTestHubQuerPodest1',1,0,1,0,4243)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBQUERPODEST (ID_HUBQUERPODEST,BEZEICHNUNG,MOTOR,OBEN,MITTIG,UNTEN,ID_SEKTOR) VALUES (4244,'CacheTestHubQuerPodest1',1,1,0,1,4244)");
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundException, QueryException {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("DELETE FROM GELENK WHERE ID_GELENK = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM GELENK WHERE ID_GELENK = 4243");
        datenbankTestInsert.datenbankUpdate("DELETE FROM GELENK WHERE ID_GELENK = 4244");
        datenbankTestInsert.datenbankUpdate("DELETE FROM SEKTOR WHERE ID_SEKTOR = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM SEKTOR WHERE ID_SEKTOR = 4243");
        datenbankTestInsert.datenbankUpdate("DELETE FROM SEKTOR WHERE ID_SEKTOR = 4244");
        datenbankTestInsert.close();
    }

    @Override
    public Cache getCache() {
        return cache; 
    }

    @Override
    public void testUpdate() throws ElementNotFoundException {
        assertTrue("CacheTestHubPodest1", cache.getById(new Long(4242)).getBezeichnung().equals("CacheTestHubPodest1"));
        assertTrue("CacheTestHubPodest1", cache.getById(new Long(4243)).getBezeichnung().equals("CacheTestHubPodest2"));
        assertTrue("CacheTestHubPodest1", cache.getById(new Long(4244)).getBezeichnung().equals("CacheTestHubPodest3"));
        
        assertTrue("CacheTestHubPodest1 -> Sektor", Objects.equals(((HubQuerPodest)cache.getById(new Long(4242))).getId_Sektor(), new Long(4242)));
        assertTrue("CacheTestHubPodest1 -> Sektor", Objects.equals(((HubQuerPodest)cache.getById(new Long(4243))).getId_Sektor(), new Long(4243)));
        assertTrue("CacheTestHubPodest1 -> Sektor", Objects.equals(((HubQuerPodest)cache.getById(new Long(4244))).getId_Sektor(), new Long(4244)));
    }

    @Override
    public void testUpdateAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}