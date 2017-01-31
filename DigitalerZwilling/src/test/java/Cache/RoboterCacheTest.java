/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenbankTestInsert.DatenbankTestInsert;
import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.Cache.RoboterCache;
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
public class RoboterCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(RoboterCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    RoboterCache cache;

    @Before
    public void setUp() throws DBNotFoundException, QueryException {
        tearDown();
        datenbankSchnittstelle.connect("./testdbConfig.cfg");
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER (ID_ROBOTER,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CacheTestRoboter1',0,0,0,0,0)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CacheTestSektor1',0,10,10,10,45)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4243,'CacheTestSektor2',0,10,20,10,45)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER_SEKTOR (ID_SEKTOR,ID_ROBOTER VALUES (4242,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO GELENK (ID_GELENK,BEZEICHNUNG,TYP,NUMMER,GELENKSTELLUNG,ID_ROBOTER) VALUES (4242,'CacheTestGELENK1','Typ1',2,0,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO GELENK (ID_GELENK,BEZEICHNUNG,TYP,NUMMER,GELENKSTELLUNG,ID_ROBOTER) VALUES (4243,'CacheTestGELENK2','Typ2',3,90,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO GELENK (ID_GELENK,BEZEICHNUNG,TYP,NUMMER,GELENKSTELLUNG,ID_ROBOTER) VALUES (4244,'CacheTestGELENK3','Typ3',4,180,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO WERKZEUG (ID_WERKZEUG,BEZEICHNUNG,ZUSTAND) VALUES (4242,'CacheTestWerkzeug1',1)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER_WERKZEUG (ID_ROBOTER,ID_WERKZEUG) VALUES (4242,4242)");
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundException, QueryException {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("DELETE FROM ROBOTER WHERE ID_ROBOTER = 4242");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CacheTestSektor1',0,10,10,10,45)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4243,'CacheTestSektor2',0,10,20,10,45)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER_SEKTOR (ID_SEKTOR,ID_ROBOTER VALUES (4242,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO GELENK (ID_GELENK,BEZEICHNUNG,TYP,NUMMER,GELENKSTELLUNG,ID_ROBOTER) VALUES (4242,'CacheTestGELENK1','Typ1',2,0,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO GELENK (ID_GELENK,BEZEICHNUNG,TYP,NUMMER,GELENKSTELLUNG,ID_ROBOTER) VALUES (4243,'CacheTestGELENK2','Typ2',3,90,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO GELENK (ID_GELENK,BEZEICHNUNG,TYP,NUMMER,GELENKSTELLUNG,ID_ROBOTER) VALUES (4244,'CacheTestGELENK3','Typ3',4,180,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO WERKZEUG (ID_WERKZEUG,BEZEICHNUNG,ZUSTAND) VALUES (4242,'CacheTestWerkzeug1',1)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER_WERKZEUG (ID_ROBOTER,ID_WERKZEUG) VALUES (4242,4242)");
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
