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
import de.hsos.digitalerzwilling.Cache.TransportbandCache;
import de.hsos.digitalerzwilling.Cache.Updater.CacheUpdateThread;
import de.hsos.digitalerzwilling.Cache.Updater.SelfTimer;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.Cache.Updater.WebSocketUpdateThread;
import de.hsos.digitalerzwilling.DatenKlassen.Transportband;
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
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author florian
 */
@RunWith(Arquillian.class)
public class TransportbandCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(TransportbandCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class,ExceptionEventHandlerScope.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    TransportbandCache cache;

    @Before
    public void setUp() throws DBNotFoundException, QueryException {
        tearDown();
        datenbankSchnittstelle.connect("./testdbConfig.cfg");
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("INSERT INTO WARENTRAEGER (ID_WARENTRAEGER,BEZEICHNUNG,MONTAGEZUSTAND,RFID_INHALT,ABSTAND_MM) VALUES (4242,'CacheTestWarentraeger1',90,'0x4242',200)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CacheTestSektor1',0,0,0,0,0)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4243,'CacheTestSektor2',0,0,0,0,0)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO TRANSPORTBAND (ID_TRANSPORTBAND,BEZEICHNUNG,REIHE,STOERUNG,LAENGE,GESCHWINDIGKEIT,ID_SEKTOR_VOR,ID_SEKTOR_NACH) VALUES (4242,'CacheTestTransportband1',1,0,100,2,4242,4243)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO TRANSPORTBAND_WARENTRAEGER (ID_TRANSPORTBAND,ID_WARENTRAEGER) VALUES (4242,4242)");
        
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundException, QueryException {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("DELETE FROM TRANSPORTBAND_WARENTRAEGER WHERE ID_TRANSPORTBAND = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM TRANSPORTBAND WHERE ID_TRANSPORTBAND = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM SEKTOR WHERE ID_SEKTOR = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM SEKTOR WHERE ID_SEKTOR = 4243");
        datenbankTestInsert.datenbankUpdate("DELETE FROM WARENTRAEGER WHERE ID_WARENTRAEGER = 4242");
        datenbankTestInsert.close();
    }

    @Override
    public Cache getCache() {
        return cache; 
    }

    @Override
    public void testUpdate() throws ElementNotFoundException, DBErrorException, DBNotFoundException, QueryException {
        assertTrue("Bezeichnung", cache.getById(new Long(4242)).getBezeichnung().equalsIgnoreCase("CacheTestTransportband1"));
        assertTrue("Laenge"     , ((Transportband)cache.getById(new Long(4242))).getLaenge()   == 100);
        assertTrue("Reihe"      , ((Transportband)cache.getById(new Long(4242))).getReihe()    == 1);
        assertTrue("Stoerung"   , ((Transportband)cache.getById(new Long(4242))).getStoerung() == 0);
        assertTrue("Geschwindigkeit", ((Transportband)cache.getById(new Long(4242))).getGeschwindigkeit() == 2);
        assertTrue("vorSektor"  , Objects.equals(((Transportband)cache.getById(new Long(4242))).getVorSektorID() , new Long(4242)));
        assertTrue("nachSektor" , Objects.equals(((Transportband)cache.getById(new Long(4242))).getNachSektorID(), new Long(4243)));
        assertTrue("WarentraegerID", !((Transportband)cache.getById(new Long(4242))).getWarentraegerIDs().isEmpty());
        if(!((Transportband)cache.getById(new Long(4242))).getWarentraegerIDs().isEmpty()){
            assertTrue("WarentaegerID", Objects.equals(((Transportband)cache.getById(new Long(4242))).getWarentraegerIDs().get(0), new Long(4242)));
        }
        
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("UPDATE TRANSPORTBAND SET STOERUNG=1,GESCHWINDIGKEIT=0 WHERE ID_TRANSPORTBAND = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM TRANSPORTBAND_WARENTRAEGER WHERE ID_TRANSPORTBAND = 4242");
        datenbankTestInsert.close();
        
        cache.update();
        
        assertTrue("Stoerung<-Update"      , ((Transportband)cache.getById(new Long(4242))).getStoerung() == 1);
        assertTrue("WarentraegerID<-Update", ((Transportband)cache.getById(new Long(4242))).getWarentraegerIDs().isEmpty());
        
        
    }

    @Override
    public void testUpdateAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
