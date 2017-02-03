/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenbankTestInsert.DatenbankTestInsert;
import de.hsos.digitalerzwilling.Cache.ArtikelCache;
import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.Cache.Exception.ElementNotFoundException;
import de.hsos.digitalerzwilling.Cache.SystemCache;
import de.hsos.digitalerzwilling.Cache.Updater.CacheUpdateThread;
import de.hsos.digitalerzwilling.Cache.Updater.SelfTimer;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.Cache.Updater.WebSocketUpdateThread;
import de.hsos.digitalerzwilling.DatenKlassen.ServerSystem;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.validation.constraints.AssertFalse;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author User
 */
@RunWith(Arquillian.class)
public class SystemCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(SystemCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    SystemCache cache;
    
    @Before
    public void setUp() throws DBNotFoundException, QueryException {
        datenbankSchnittstelle.connect("./testdbConfig.cfg");
    }

    @Override
    public void testUpdate() throws DBNotFoundException, QueryException, DBErrorException, ElementNotFoundException {
        try {
            Thread.sleep(90000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SystemCacheTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("SPS "       + ((ServerSystem) cache.getById(0L)).isSpsFehlerStatus());
        assertTrue("SPSFehler",           ((ServerSystem) cache.getById(0L)).isSpsFehlerStatus());
        
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("UPDATE HEARTBEAT SET ZEITSTEMPEL = CURRENT_TIMESTAMP");
        datenbankTestInsert.close();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SystemCacheTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("SPS "       + ((ServerSystem) cache.getById(0L)).isSpsFehlerStatus());
        assertFalse("SPSFehler",          ((ServerSystem) cache.getById(0L)).isSpsFehlerStatus());
    }

    @Override
    public void testUpdateAll() throws ElementNotFoundException, DBNotFoundException, QueryException {
        assertFalse("DatenbankFehler", ((ServerSystem) cache.getById(0L)).isDatenbankFehlerStatus());
        assertFalse("SPSFehler",       ((ServerSystem) cache.getById(0L)).isSpsFehlerStatus());
    }

    @Override
    public Cache getCache() {
        return cache;
    }
    
}
