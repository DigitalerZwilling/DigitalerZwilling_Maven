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
import de.hsos.digitalerzwilling.Cache.HubQuerPodestCache;
import de.hsos.digitalerzwilling.Cache.Updater.CacheUpdateThread;
import de.hsos.digitalerzwilling.Cache.Updater.SelfTimer;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.Cache.Updater.WebSocketUpdateThread;
import de.hsos.digitalerzwilling.DatenKlassen.HubQuerPodest;
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
            .addClasses(HubQuerPodestCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class)
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
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBQUERPODEST (ID_HUBQUERPODEST,BEZEICHNUNG,MOTOR,OBEN,MITTIG,UNTEN,ID_SEKTOR) VALUES (4243,'CacheTestHubQuerPodest2',1,0,1,0,4243)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBQUERPODEST (ID_HUBQUERPODEST,BEZEICHNUNG,MOTOR,OBEN,MITTIG,UNTEN,ID_SEKTOR) VALUES (4244,'CacheTestHubQuerPodest3',1,0,0,1,4244)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO Hubquerpodest_Hubquerpodest (ID_HUBQUERPODEST1,ID_HUBQUERPODEST2) VALUES (4242,4243)");
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundException, QueryException {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("DELETE FROM Hubquerpodest_Hubquerpodest WHERE ID_HUBQUERPODEST1 = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM HUBQUERPODEST WHERE ID_HUBQUERPODEST = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM HUBQUERPODEST WHERE ID_HUBQUERPODEST = 4243");
        datenbankTestInsert.datenbankUpdate("DELETE FROM HUBQUERPODEST WHERE ID_HUBQUERPODEST = 4244");
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
        assertTrue("CacheTestHubPodest1", cache.getById(4242L).getBezeichnung().equals("CacheTestHubQuerPodest1"));
        assertTrue("CacheTestHubPodest2", cache.getById(4243L).getBezeichnung().equals("CacheTestHubQuerPodest2"));
        assertTrue("CacheTestHubPodest3", cache.getById(4244L).getBezeichnung().equals("CacheTestHubQuerPodest3"));
        
        assertTrue("CacheTestHubPodest1 -> Sektor", Objects.equals(((HubQuerPodest)cache.getById(4242L)).getId_Sektor(), 4242L));
        assertTrue("CacheTestHubPodest2 -> Sektor", Objects.equals(((HubQuerPodest)cache.getById(4243L)).getId_Sektor(), 4243L));
        assertTrue("CacheTestHubPodest3 -> Sektor", Objects.equals(((HubQuerPodest)cache.getById(4244L)).getId_Sektor(), 4244L));
        
        assertTrue("GroupID", !((HubQuerPodest)cache.getById(4242L)).getGruppenIDs().isEmpty());
        
        assertTrue("CacheTestHubQuerPodest1 -> Zustand", ((HubQuerPodest)cache.getById(4242L)).isMotor() == true  &&
                                                         ((HubQuerPodest)cache.getById(4242L)).isOben()  == true  &&
                                                         ((HubQuerPodest)cache.getById(4242L)).isMittig()== false &&
                                                         ((HubQuerPodest)cache.getById(4242L)).isUnten() == false);
        
        assertTrue("CacheTestHubQuerPodest1 -> Zustand", ((HubQuerPodest)cache.getById(4243L)).isMotor() == true  &&
                                                         ((HubQuerPodest)cache.getById(4243L)).isOben()  == false &&
                                                         ((HubQuerPodest)cache.getById(4243L)).isMittig()== true  &&
                                                         ((HubQuerPodest)cache.getById(4243L)).isUnten() == false);
        
        assertTrue("CacheTestHubQuerPodest1 -> Zustand", ((HubQuerPodest)cache.getById(4244L)).isMotor() == true  &&
                                                         ((HubQuerPodest)cache.getById(4244L)).isOben()  == false &&
                                                         ((HubQuerPodest)cache.getById(4244L)).isMittig()== false &&
                                                         ((HubQuerPodest)cache.getById(4244L)).isUnten() == true);
        
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("UPDATE HUBQUERPODEST SET MOTOR=0,OBEN=0,MITTIG=1,UNTEN=0 WHERE ID_HUBQUERPODEST=4242");
        datenbankTestInsert.datenbankUpdate("UPDATE HUBQUERPODEST SET MOTOR=0,OBEN=0,MITTIG=0,UNTEN=1 WHERE ID_HUBQUERPODEST=4243");
        datenbankTestInsert.datenbankUpdate("UPDATE HUBQUERPODEST SET MOTOR=0,OBEN=1,MITTIG=0,UNTEN=0 WHERE ID_HUBQUERPODEST=4244");
        datenbankTestInsert.close();
        
        cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();
        cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();
        cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();
        cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();
        cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();cache.update();
        
        assertTrue("CacheTestHubQuerPodest1 -> Zustand(Update)", ((HubQuerPodest)cache.getById(4242L)).isMotor() == false  &&
                                                                 ((HubQuerPodest)cache.getById(4242L)).isOben()  == false  &&
                                                                 ((HubQuerPodest)cache.getById(4242L)).isMittig()== true   &&
                                                                 ((HubQuerPodest)cache.getById(4242L)).isUnten() == false);
        
        assertTrue("CacheTestHubQuerPodest2 -> Zustand(Update)", ((HubQuerPodest)cache.getById(4243L)).isMotor() == false  &&
                                                                 ((HubQuerPodest)cache.getById(4243L)).isOben()  == false  &&
                                                                 ((HubQuerPodest)cache.getById(4243L)).isMittig()== false  &&
                                                                 ((HubQuerPodest)cache.getById(4243L)).isUnten() == true);
        
        assertTrue("CacheTestHubQuerPodest3 -> Zustand(Update)", ((HubQuerPodest)cache.getById(4244L)).isMotor() == false  &&
                                                                 ((HubQuerPodest)cache.getById(4244L)).isOben()  == true   &&
                                                                 ((HubQuerPodest)cache.getById(4244L)).isMittig()== false  &&
                                                                 ((HubQuerPodest)cache.getById(4244L)).isUnten() == false);
    }

    @Override
    public void testUpdateAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
