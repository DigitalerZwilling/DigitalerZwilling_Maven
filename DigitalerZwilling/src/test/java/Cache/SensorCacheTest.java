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
import de.hsos.digitalerzwilling.Cache.SensorCache;
import de.hsos.digitalerzwilling.Cache.Updater.CacheUpdateThread;
import de.hsos.digitalerzwilling.Cache.Updater.SelfTimer;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.Cache.Updater.WebSocketUpdateThread;
import de.hsos.digitalerzwilling.DatenKlassen.Sensor;
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
public class SensorCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(SensorCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xmlthrows DBNotFoundException ");
    }
    
    @Inject
    SensorCache cache;

    @Before
    public void setUp() throws DBNotFoundException, QueryException{
        tearDown();
        datenbankSchnittstelle.connect("./testdbConfig.cfg");
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CacheTestSektor1',0,0,0,0,0)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SENSOR (ID_SENSOR,BEZEICHNUNG,STOERUNG,ZUSTAND,PHY_ADRESSE,ID_SEKTOR) VALUES (4242,'CacheTestSensor1',0,0,'1234',4242");
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundException, QueryException {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("DELETE FROM SENSOR WHERE ID_SENSOR = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM SEKTOR WHERE ID_SEKTOR = 4242");
        datenbankTestInsert.close();
    }

    @Override
    public Cache getCache() {
        return cache; 
    }

    @Override
    public void testUpdate() throws ElementNotFoundException, DBNotFoundException, QueryException, DBErrorException {
        assertTrue("GroupID", ((Sensor)cache.getById(new Long(4242))).getBezeichnung().equalsIgnoreCase("CacheTestSensor1"));
        assertTrue("GroupID", ((Sensor)cache.getById(new Long(4242))).getStoerung()==0);
        assertTrue("GroupID", !((Sensor)cache.getById(new Long(4242))).isZustand());
        assertTrue("GroupID", ((Sensor)cache.getById(new Long(4242))).getPhy_adresse().equalsIgnoreCase("1234"));
        assertTrue("GroupID", Objects.equals(((Sensor)cache.getById(new Long(4242))).getSektorID(), new Long(4242)));
        
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("UPDATE SENSOR SET ZUSTAND=1,STOERUNG=1 WHERE ID_WERKZEUG=4242");
        datenbankTestInsert.close();
        
        cache.update();
        
        assertTrue("GroupID", ((Sensor)cache.getById(new Long(4242))).getStoerung()==1);
        assertTrue("GroupID", ((Sensor)cache.getById(new Long(4242))).isZustand());
        
    }

    @Override
    public void testUpdateAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
