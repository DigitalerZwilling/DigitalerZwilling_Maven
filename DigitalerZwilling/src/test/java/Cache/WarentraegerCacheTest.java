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
import de.hsos.digitalerzwilling.Cache.WarentraegerCache;
import de.hsos.digitalerzwilling.DatenKlassen.Warentraeger;
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
public class WarentraegerCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(WarentraegerCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    WarentraegerCache cache;

    @Before
    public void setUp() throws DBNotFoundException, QueryException {
        tearDown();
        datenbankSchnittstelle.connect("./testdbConfig.cfg");
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("INSERT INTO ARTIKEL (ID_ARTIKEL,BEZEICHNUNG) VALUES (4242,'CacheTestArtikel1')");
        datenbankTestInsert.datenbankUpdate("INSERT INTO WARENTRAEGER (ID_WARENTRAEGER,BEZEICHNUNG, STOERUNG,"
                + "MONTAGEZUSTAND,RFID_INHALT,ABSTAND_MM) VALUES "
                + "(4242,'CacheTestWarentraeger1',0,100,'FOOBAR',42)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ARTIKEL_WARENTRAEGER (ID_ARTIKEL, ID_WARENTRAEGER) VALUES (4242,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CacheTestSektor1',0,0,0,0,0)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4243,'CacheTestSektor2',0,0,0,0,0)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO TRANSPORTBAND (ID_TRANSPORTBAND,BEZEICHNUNG,REIHE,STOERUNG,LAENGE,GESCHWINDIGKEIT,ID_SEKTOR_VOR,ID_SEKTOR_NACH) VALUES (4242,'CacheTestTransportband1',1,0,100,2,4242,4243)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO TRANSPORTBAND_WARENTRAEGER (ID_TRANSPORTBAND,ID_WARENTRAEGER) VALUES (4242,4242)");
        
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundException, QueryException {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("DELETE FROM ARTIKEL_WARENTRAEGER WHERE ID_WARENTRAEGER = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM ARTIKEL WHERE ID_ARTIKEL = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM TRANSPORTBAND_WARENTRAEGER WHERE ID_WARENTRAEGER = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM SEKTOR_WARENTRAEGER WHERE ID_WARENTRAEGER = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM WARENTRAEGER WHERE ID_WARENTRAEGER = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM TRANSPORTBAND WHERE ID_TRANSPORTBAND = 4242");
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
        assertTrue("WarentraegerCachetest1", cache.getById(4242L).getBezeichnung().equalsIgnoreCase("CacheTestWarentraeger1"));
        
        assertTrue("Stoerung",       ((Warentraeger)cache.getById(4242L)).getStoerung()==0);
        assertTrue("Montage",        ((Warentraeger)cache.getById(4242L)).getMontagezustand()==100);
        assertTrue("RFID",           ((Warentraeger)cache.getById(4242L)).getrFID_inhalt().equalsIgnoreCase("FOOBAR"));
        assertTrue("Abstand",        ((Warentraeger)cache.getById(4242L)).getAbstand_mm()==42);
        assertTrue("TransportID",   !((Warentraeger)cache.getById(4242L)).getTransportbandIDs().isEmpty());
        if(!((Warentraeger)cache.getById(4242L)).getTransportbandIDs().isEmpty()){
            assertTrue("TransportID",((Warentraeger)cache.getById(4242L)).getTransportbandIDs().get(0)==4242);
        }
        assertTrue("SektorID",       ((Warentraeger)cache.getById(4242L)).getSektorIDs().isEmpty());
        
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("UPDATE WARENTRAEGER SET STOERUNG=1,MONTAGEZUSTAND=99,ABSTAND_MM=84 WHERE ID_WARENTRAEGER=4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM TRANSPORTBAND_WARENTRAEGER WHERE ID_WARENTRAEGER=4242");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR_WARENTRAEGER (ID_SEKTOR,ID_WARENTRAEGER) VALUES (4242,4242)");
        datenbankTestInsert.close();
        
        cache.update();
        
        assertTrue("Stoerung",       ((Warentraeger)cache.getById(4242L)).getStoerung()==1);
        assertTrue("Montage",        ((Warentraeger)cache.getById(4242L)).getMontagezustand()==99);
        assertTrue("Abstand",        ((Warentraeger)cache.getById(4242L)).getAbstand_mm()==84);
        assertTrue("TransportID",    ((Warentraeger)cache.getById(4242L)).getTransportbandIDs().isEmpty());
        assertTrue("SektorID",      !((Warentraeger)cache.getById(4242L)).getSektorIDs().isEmpty());
        if(!((Warentraeger)cache.getById(4242L)).getTransportbandIDs().isEmpty()){
            assertTrue("SektorID",   ((Warentraeger)cache.getById(4242L)).getSektorIDs().get(0)==4242);
        }
    }

    @Override
    public void testUpdateAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
