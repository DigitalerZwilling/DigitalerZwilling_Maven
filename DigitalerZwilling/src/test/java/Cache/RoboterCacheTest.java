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
import de.hsos.digitalerzwilling.Cache.RoboterCache;
import de.hsos.digitalerzwilling.Cache.Updater.CacheUpdateThread;
import de.hsos.digitalerzwilling.Cache.Updater.SelfTimer;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.Cache.Updater.WebSocketUpdateThread;
import de.hsos.digitalerzwilling.DatenKlassen.Roboter;
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
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER (ID_ROBOTER,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CacheTestRoboter1',0,1,2,3,4)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4242,'CacheTestSektor1',0,10,10,10,45)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO SEKTOR (ID_SEKTOR,BEZEICHNUNG,STOERUNG,POSITION_X,POSITION_Y,POSITION_Z,POSITION_AUSRICHTUNG) VALUES (4243,'CacheTestSektor2',0,10,20,10,45)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER_SEKTOR (ID_SEKTOR,ID_ROBOTER) VALUES (4242,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO GELENK (ID_GELENK,BEZEICHNUNG,TYP,NUMMER,GELENKSTELLUNG,ID_ROBOTER) VALUES (4242,'CacheTestGELENK1','Typ1',2,0,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO GELENK (ID_GELENK,BEZEICHNUNG,TYP,NUMMER,GELENKSTELLUNG,ID_ROBOTER) VALUES (4243,'CacheTestGELENK2','Typ2',3,90,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO GELENK (ID_GELENK,BEZEICHNUNG,TYP,NUMMER,GELENKSTELLUNG,ID_ROBOTER) VALUES (4244,'CacheTestGELENK3','Typ3',4,180,4242)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO WERKZEUG (ID_WERKZEUG,BEZEICHNUNG,ZUSTAND) VALUES (4242,'CacheTestWerkzeug1',1)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO WERKZEUG (ID_WERKZEUG,BEZEICHNUNG,ZUSTAND) VALUES (4243,'CacheTestWerkzeug2',1)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER_WERKZEUG (ID_ROBOTER,ID_WERKZEUG) VALUES (4242,4242)");
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundException, QueryException {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("DELETE FROM ROBOTER_WERKZEUG WHERE ID_ROBOTER = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM ROBOTER_SEKTOR WHERE ID_SEKTOR = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM ROBOTER_SEKTOR WHERE ID_SEKTOR = 4243");
        datenbankTestInsert.datenbankUpdate("DELETE FROM SEKTOR WHERE ID_SEKTOR = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM SEKTOR WHERE ID_SEKTOR =  4243");
        datenbankTestInsert.datenbankUpdate("DELETE FROM GELENK WHERE ID_ROBOTER = 4242");
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
        updater.setUpdate(false);
        
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("UPDATE ROBOTER SET POSITION_AUSRICHTUNG = 5, STOERUNG = 1, POSITION_X = 2,"
                                          + " POSITION_Y = 3, POSITION_Z = 4 WHERE ID_ROBOTER = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM ROBOTER_SEKTOR WHERE ID_SEKTOR = 4242");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER_SEKTOR (ID_SEKTOR,ID_ROBOTER) VALUES (4243,4242)");
        datenbankTestInsert.datenbankUpdate("DELETE FROM ROBOTER_WERKZEUG WHERE ID_ROBOTER = 4242");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ROBOTER_WERKZEUG(ID_WERKZEUG,ID_ROBOTER) VALUES (4243,4242)");
        datenbankTestInsert.close();
        
        cache.toggleState();
        cache.update();
        cache.toggleState();
        
        assertTrue("Bezeichnung", cache.getById(4242L).getBezeichnung().equalsIgnoreCase("CacheTestRoboter1"));
        assertTrue("GelenkIDs",   !((Roboter) cache.getById(4242L)).getId_Gelenke().isEmpty());
        assertTrue("SektorIDs",   !((Roboter) cache.getById(4242L)).getId_Sektor().isEmpty() 
                                   && ((Roboter) cache.getById(4242L)).getId_Sektor().get(0)==4243);
        assertTrue("WerkzeugIDs", !((Roboter) cache.getById(4242L)).getId_Werkzeug().isEmpty()
                                   && ((Roboter) cache.getById(4242L)).getId_Werkzeug().get(0)==4243);
        assertTrue("Stoerung",    ((Roboter) cache.getById(4242L)).getStoerung()==1);
        assertTrue("X_Pos",       ((Roboter) cache.getById(4242L)).getX()==2);
        assertTrue("Y_Pos",       ((Roboter) cache.getById(4242L)).getY()==3);
        assertTrue("Z_Pos",       ((Roboter) cache.getById(4242L)).getZ()==4);
        assertTrue("Ausrichtung", ((Roboter) cache.getById(4242L)).getAusrichtung()==5);
        
        updater.setUpdate(true);
    }

    @Override
    public void testUpdateAll() throws ElementNotFoundException {
        assertTrue("Bezeichnung", cache.getById(4242L).getBezeichnung().equalsIgnoreCase("CacheTestRoboter1"));
        assertTrue("Ausrichtung", ((Roboter) cache.getById(4242L)).getAusrichtung()==4);
        assertTrue("GelenkIDs",   !((Roboter) cache.getById(4242L)).getId_Gelenke().isEmpty());
        assertTrue("SektorIDs",   !((Roboter) cache.getById(4242L)).getId_Sektor().isEmpty() 
                                   && ((Roboter) cache.getById(4242L)).getId_Sektor().get(0)==4242);
        assertTrue("WerkzeugIDs", !((Roboter) cache.getById(4242L)).getId_Werkzeug().isEmpty()
                                   && ((Roboter) cache.getById(4242L)).getId_Werkzeug().get(0)==4242);
        assertTrue("Stoerung",    ((Roboter) cache.getById(4242L)).getStoerung()==0);
        assertTrue("X_Pos",       ((Roboter) cache.getById(4242L)).getX()==1);
        assertTrue("Y_Pos",       ((Roboter) cache.getById(4242L)).getY()==2);
        assertTrue("Z_Pos",       ((Roboter) cache.getById(4242L)).getZ()==3);
    }
}
