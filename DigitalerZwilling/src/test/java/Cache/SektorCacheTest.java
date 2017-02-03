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
import de.hsos.digitalerzwilling.Cache.SektorCache;
import de.hsos.digitalerzwilling.Cache.Updater.CacheUpdateThread;
import de.hsos.digitalerzwilling.Cache.Updater.SelfTimer;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.Cache.Updater.WebSocketUpdateThread;
import de.hsos.digitalerzwilling.DatenKlassen.Sektor;
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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author florian
 */
@RunWith(Arquillian.class)
public class SektorCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(SektorCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    SektorCache cache;
    
    @Before
    public void setUp() throws DBNotFoundException, QueryException {
        tearDown();
        datenbankSchnittstelle.connect("./testdbConfig.cfg");
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("INSERT INTO WARENTRAEGER (ID_WARENTRAEGER,BEZEICHNUNG, STOERUNG,"
                + "MONTAGEZUSTAND,RFID_INHALT,ABSTAND_MM) VALUES "
                + "(4242,'CacheTestWarentraeger1',0,100,'FOOBAR',42)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CacheTestSektor1',0,10,20,30,45)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4243,'CacheTestSektor2',0,10,20,10,45)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO TRANSPORTBAND (ID_TRANSPORTBAND,BEZEICHNUNG,REIHE,STOERUNG,LAENGE,GESCHWINDIGKEIT,ID_SEKTOR_VOR,ID_SEKTOR_NACH) VALUES (4242,'CacheTestTransportband1',1,0,100,2,4242,4243)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO TRANSPORTBAND (ID_TRANSPORTBAND,BEZEICHNUNG,REIHE,STOERUNG,LAENGE,GESCHWINDIGKEIT,ID_SEKTOR_VOR,ID_SEKTOR_NACH) VALUES (4243,'CacheTestTransportband1',1,0,100,2,4243,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR_WARENTRAEGER (ID_SEKTOR,ID_WARENTRAEGER) VALUES (4242,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SENSOR (ID_SENSOR,BEZEICHNUNG,STOERUNG,ZUSTAND,PHY_ADRESSE,ID_SEKTOR) VALUES (4242,'CacheTestSensor1',0,0,'1234',4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBPODEST (ID_HUBPODEST,BEZEICHNUNG,OBEN,UNTEN,ID_SEKTOR) VALUES (4242,'CacheTestHubPodest1',1,0,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO HUBQUERPODEST (ID_HUBQUERPODEST,BEZEICHNUNG,MOTOR,OBEN,MITTIG,UNTEN,ID_SEKTOR) VALUES (4242,'CacheTestHubQuerPodest1',1,1,0,0,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER (ID_ROBOTER,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CacheTestRoboter1',0,1,2,3,4)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER_SEKTOR (ID_ROBOTER,ID_SEKTOR) VALUES (4242,4242)");
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundException, QueryException {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("DELETE FROM ROBOTER_SEKTOR WHERE ID_ROBOTER = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM SEKTOR_WARENTRAEGER WHERE ID_WARENTRAEGER = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM WARENTRAEGER WHERE ID_WARENTRAEGER = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM TRANSPORTBAND WHERE ID_TRANSPORTBAND = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM TRANSPORTBAND WHERE ID_TRANSPORTBAND = 4243");
        datenbankTestInsert.datenbankUpdate("DELETE FROM SENSOR WHERE ID_SENSOR = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM HUBPODEST WHERE ID_HUBPODEST = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM HUBQUERPODEST WHERE ID_HUBQUERPODEST = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM ROBOTER WHERE ID_ROBOTER = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM SEKTOR WHERE ID_SEKTOR = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM SEKTOR WHERE ID_SEKTOR = 4243");
        datenbankTestInsert.close();
    }

    @Override
    public Cache getCache() {
        return cache; 
    }

    @Override
    public void testUpdate() throws ElementNotFoundException, DBNotFoundException, QueryException, DBErrorException {
        updater.setUpdate(false);
        
        
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("UPDATE SEKTOR SET STOERUNG = 1 WHERE ID_SEKTOR = 4242");
        datenbankTestInsert.close();
        
        cache.toggleState();
        cache.update();  
        cache.toggleState();
        
        assertTrue("Stoerung (Update)", ((Sektor) cache.getById(4242L)).getStoerung() == 1);
        updater.setUpdate(true);
    }

    @Override
    public void testUpdateAll() throws ElementNotFoundException {
        assertTrue("Ausrichtung",    ((Sektor) cache.getById(4242L)).getBezeichnung().equalsIgnoreCase("CacheTestSektor1"));
        assertTrue("Ausrichtung",    ((Sektor) cache.getById(4242L)).getAusrichtung()==45);
        assertTrue("HubPodest",     !((Sektor) cache.getById(4242L)).getHubpodestIDs().isEmpty() && ((Sektor) cache.getById(4242L)).getHubpodestIDs().get(0) == 4242);
        assertTrue("HubQuerPodest", !((Sektor) cache.getById(4242L)).getHubquerpodestIDs().isEmpty() && ((Sektor) cache.getById(4242L)).getHubquerpodestIDs().get(0) == 4242);
        assertTrue("NachTransport", !((Sektor) cache.getById(4242L)).getNachTransportbandIDs().isEmpty() && ((Sektor) cache.getById(4242L)).getNachTransportbandIDs().get(0) == 4242);
        assertTrue("Roboter",       !((Sektor) cache.getById(4242L)).getRoboterIDs().isEmpty() && ((Sektor) cache.getById(4242L)).getRoboterIDs().get(0) == 4242);
        assertTrue("Sensor",        !((Sektor) cache.getById(4242L)).getSensorIDs().isEmpty() && ((Sektor) cache.getById(4242L)).getSensorIDs().get(0) == 4242);
        assertTrue("VorTransport",  !((Sektor) cache.getById(4242L)).getVorTransportbandIDs().isEmpty() && ((Sektor) cache.getById(4242L)).getVorTransportbandIDs().get(0) == 4243);
        assertTrue("Warentraeger",  !((Sektor) cache.getById(4242L)).getWarentraegerIDs().isEmpty() && ((Sektor) cache.getById(4242L)).getWarentraegerIDs().get(0) == 4242);
        assertTrue("Stoerung",       ((Sektor) cache.getById(4242L)).getStoerung() == 0);
        assertTrue("X",              ((Sektor) cache.getById(4242L)).getX()==10);
        assertTrue("Y",              ((Sektor) cache.getById(4242L)).getY()==20);
        assertTrue("Z",              ((Sektor) cache.getById(4242L)).getZ()==30);
    }
    
}
