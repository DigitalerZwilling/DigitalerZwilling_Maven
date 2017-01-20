/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenbankTestInsert.DatenbankTestInsert;
import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.Cache.HubPodestCache;
import de.hsos.digitalerzwilling.Cache.Updater.CacheUpdateThread;
import de.hsos.digitalerzwilling.Cache.Updater.SelfTimer;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.Cache.Updater.WebSocketUpdateThread;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
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
public class HubPodestCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(HubPodestCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    HubPodestCache cache;
    
    @Inject
    DatenbankSchnittstelle datenbankSchnittstelle;
    
    @Before
    public void setUp() throws DBNotFoundException, QueryException {
        tearDown();
        datenbankSchnittstelle.connect("./testdbConfig.cfg");
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBQUERPODEST (ID_HUBQUERPODEST,BEZEICHNUNG,MOTOR,OBEN,MITTIG,UNTEN) VALUES (4242,'CacheTestHubQuerPodest1',1,1,0,0)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBQUERPODEST (ID_HUBQUERPODEST,BEZEICHNUNG,MOTOR,OBEN,MITTIG,UNTEN) VALUES (4243,'CacheTestHubQuerPodest1',1,0,1,0)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBQUERPODEST (ID_HUBQUERPODEST,BEZEICHNUNG,MOTOR,OBEN,MITTIG,UNTEN) VALUES (4244,'CacheTestHubQuerPodest1',1,1,0,1)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBQUERPODEST (ID_HUBQUERPODEST) VALUES (4245)");
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundException, QueryException {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("DELETE FROM GELENK WHERE ID_GELENK = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM GELENK WHERE ID_GELENK = 4243");
        datenbankTestInsert.datenbankUpdate("DELETE FROM GELENK WHERE ID_GELENK = 4244");
        datenbankTestInsert.datenbankUpdate("DELETE FROM GELENK WHERE ID_GELENK = 4245");
        datenbankTestInsert.close();
    }

    @Override
    public Cache getCache() {
        return cache; 
    }

    @Override
    public void testUpdate() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void testUpdateAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
