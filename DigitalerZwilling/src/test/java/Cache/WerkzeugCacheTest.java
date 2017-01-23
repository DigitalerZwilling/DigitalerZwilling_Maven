/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.Cache.Updater.CacheUpdateThread;
import de.hsos.digitalerzwilling.Cache.Updater.SelfTimer;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.Cache.Updater.WebSocketUpdateThread;
import de.hsos.digitalerzwilling.Cache.WerkzeugCache;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import de.hsos.digitalerzwilling.Websockets.ExceptionEventHandlerScope;
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
public class WerkzeugCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(WerkzeugCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class,ExceptionEventHandlerScope.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    WerkzeugCache cache;

    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
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
