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
import de.hsos.digitalerzwilling.Cache.HubPodestCache;
import de.hsos.digitalerzwilling.Cache.Updater.CacheUpdateThread;
import de.hsos.digitalerzwilling.Cache.Updater.SelfTimer;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.Cache.Updater.WebSocketUpdateThread;
import de.hsos.digitalerzwilling.DatenKlassen.HubPodest;
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
public class HubPodestCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(HubPodestCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    HubPodestCache cache;
    
    @Before
    public void setUp() throws DBNotFoundException, QueryException {
        tearDown();
        datenbankSchnittstelle.connect("./testdbConfig.cfg");
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CACHETESTSEKTOR1',0,0,0,0,0)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4243,'CACHETESTSEKTOR2',0,1,4,6,90)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4244,'CACHETESTSEKTOR2',0,2,5,7,180)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBPODEST (ID_HUBPODEST,BEZEICHNUNG,OBEN,UNTEN,ID_SEKTOR) VALUES (4242,'CacheTestHubPodest1',1,0,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBPODEST (ID_HUBPODEST,BEZEICHNUNG,OBEN,UNTEN,ID_SEKTOR) VALUES (4243,'CacheTestHubPodest2',0,1,4243)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBPODEST (ID_HUBPODEST,BEZEICHNUNG,OBEN,UNTEN,ID_SEKTOR) VALUES (4244,'CacheTestHubPodest3',1,0,4244)");
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundException, QueryException {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("DELETE FROM HUBPODEST WHERE ID_HUBPODEST = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM HUBPODEST WHERE ID_HUBPODEST = 4243");
        datenbankTestInsert.datenbankUpdate("DELETE FROM HUBPODEST WHERE ID_HUBPODEST = 4244");
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
    public void testUpdate() throws ElementNotFoundException, DBNotFoundException, QueryException, DBErrorException {
        assertTrue("CacheTestHubPodest1", cache.getById(4242L).getBezeichnung().equals("CacheTestHubPodest1"));
        assertTrue("CacheTestHubPodest2", cache.getById(4243L).getBezeichnung().equals("CacheTestHubPodest2"));
        assertTrue("CacheTestHubPodest3", cache.getById(4244L).getBezeichnung().equals("CacheTestHubPodest3"));
        
        assertTrue("CacheTestHubPodest1 -> Sektor", Objects.equals(((HubPodest)cache.getById(4242L)).getId_sektor(), 4242L));
        assertTrue("CacheTestHubPodest1 -> Sektor", Objects.equals(((HubPodest)cache.getById(4243L)).getId_sektor(), 4243L));
        assertTrue("CacheTestHubPodest1 -> Sektor", Objects.equals(((HubPodest)cache.getById(4244L)).getId_sektor(), 4244L));
        
        System.out.println(((HubPodest)cache.getById(4242L)).isOben()+ " "+((HubPodest)cache.getById(4242L)).isUnten());
        
        assertTrue("CacheTestHubPodest1 -> Zustand", ((HubPodest)cache.getById(4242L)).isOben() == true &&
                                                     ((HubPodest)cache.getById(4242L)).isUnten()== false );
        assertTrue("CacheTestHubPodest2 -> Zustand", ((HubPodest)cache.getById(4243L)).isOben() == false &&
                                                     ((HubPodest)cache.getById(4243L)).isUnten()== true );
        assertTrue("CacheTestHubPodest3 -> Zustand", ((HubPodest)cache.getById(4244L)).isOben() == true &&
                                                     ((HubPodest)cache.getById(4244L)).isUnten()== false );
        
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("UPDATE HUBPODEST SET OBEN=0,UNTEN=1 WHERE ID_HUBPODEST=4242");
        datenbankTestInsert.datenbankUpdate("UPDATE HUBPODEST SET OBEN=1,UNTEN=0 WHERE ID_HUBPODEST=4243");
        datenbankTestInsert.datenbankUpdate("UPDATE HUBPODEST SET OBEN=0,UNTEN=0 WHERE ID_HUBPODEST=4244");
        datenbankTestInsert.close();
        
        cache.update();
        
        assertTrue("CacheTestHubPodest1 -> Zustand(Update)", ((HubPodest)cache.getById(4242L)).isOben() == false &&
                                                             ((HubPodest)cache.getById(4242L)).isUnten()== true );
        assertTrue("CacheTestHubPodest2 -> Zustand(Update)", ((HubPodest)cache.getById(4243L)).isOben() == true &&
                                                             ((HubPodest)cache.getById(4243L)).isUnten()== false );
        assertTrue("CacheTestHubPodest3 -> Zustand(Update)", ((HubPodest)cache.getById(4244L)).isOben() == false &&
                                                             ((HubPodest)cache.getById(4244L)).isUnten()== false );
    }

    @Override
    public void testUpdateAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
