/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import de.hsos.digitalerzwilling.Cache.Exception.DBErrorException;
import de.hsos.digitalerzwilling.Cache.Updater.CacheUpdateThread;
import de.hsos.digitalerzwilling.Cache.Updater.SelfTimer;
import de.hsos.digitalerzwilling.Cache.Updater.Updater;
import de.hsos.digitalerzwilling.Cache.Updater.WebSocketUpdateThread;
import de.hsos.digitalerzwilling.DatenKlassen.Element;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import DatenbankTestInsert.DatenbankTestInsert;
import de.hsos.digitalerzwilling.Cache.ArtikelCache;
import de.hsos.digitalerzwilling.Cache.Cache;
import de.hsos.digitalerzwilling.Cache.Exception.ElementNotFoundException;
import de.hsos.digitalerzwilling.DatenKlassen.Artikel;
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
public class ArtikelCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(ArtikelCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    ArtikelCache cache;
    
    /**
     * Test of update method, of class ArtikelCache.
     */
    
    @Before
    public void setUp() throws DBNotFoundException, QueryException {
        tearDown();
        datenbankSchnittstelle.connect("./testdbConfig.cfg");
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("INSERT INTO ARTIKEL (ID_ARTIKEL,BEZEICHNUNG) VALUES (4242,'CacheTestArtikel1')");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ARTIKEL (ID_ARTIKEL,BEZEICHNUNG) VALUES (4243,'CacheTestArtikel2')");
        datenbankTestInsert.datenbankUpdate("INSERT INTO WARENTRAEGER (ID_WARENTRAEGER,BEZEICHNUNG, STOERUNG,"
                + "MONTAGEZUSTAND,RFID_INHALT,ABSTAND_MM) VALUES "
                + "(4242,'CacheTestWarentraeger1',0,100,'FOOBAR',42)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ARTIKEL_WARENTRAEGER (ID_ARTIKEL, ID_WARENTRAEGER) VALUES (4242,4242)");
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundException, QueryException {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("DELETE FROM ARTIKEL_WARENTRAEGER WHERE ID_WARENTRAEGER = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM ARTIKEL_WARENTRAEGER WHERE ID_WARENTRAEGER = 4243");
        datenbankTestInsert.datenbankUpdate("DELETE FROM ARTIKEL WHERE ID_ARTIKEL = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM ARTIKEL WHERE ID_ARTIKEL = 4243");
        datenbankTestInsert.datenbankUpdate("DELETE FROM WARENTRAEGER WHERE ID_WARENTRAEGER = 4242");
        datenbankTestInsert.datenbankUpdate("DELETE FROM WARENTRAEGER WHERE ID_WARENTRAEGER = 4243");
        datenbankTestInsert.close();
    }

    @Override
    public Cache getCache() {
        return cache;
    }

    @Override
    public void testUpdate() throws DBNotFoundException, QueryException, DBErrorException, ElementNotFoundException {
        Element element1;
        Element element2;
        for(Element element: cache.getAll()){
            if(element.getBezeichnung().equals("CacheTestArtikel1'"))
                element1 = element;
            
            if(element.getBezeichnung().equals("CacheTestArtikel2'"))
                element2 = element;
        }
        
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("UPDATE ARTIKEL SET BEZEICHNUNG='CacheTestArtikel12' WHERE BEZEICHNUNG LIKE 'CacheTestArtikel1'");
        datenbankTestInsert.datenbankUpdate("UPDATE ARTIKEL SET BEZEICHNUNG='CacheTestArtikel22' WHERE BEZEICHNUNG LIKE 'CacheTestArtikel2'");
        
        if(((Artikel)cache.getById(4242L)).getId_Warentraeger().size()<=0){
            assertTrue("Warentraeger nicht gefunden.",false);
            return;
        }
        
        if(((Artikel)cache.getById(4243L)).getId_Warentraeger().size()>0){
            assertTrue("Fehlerhafter Warentraeger eintrag.",false);
            return;
        }
        
        datenbankTestInsert.datenbankUpdate("INSERT INTO WARENTRAEGER (ID_WARENTRAEGER,BEZEICHNUNG, STOERUNG,"
                + "MONTAGEZUSTAND,RFID_INHALT,ABSTAND_MM) VALUES "
                + "(4243,'CacheTestWarentraeger1',0,100,'FOOBAR',42)");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ARTIKEL_WARENTRAEGER (ID_ARTIKEL, ID_WARENTRAEGER) VALUES (4243,4243)");
        datenbankTestInsert.close();
        
        cache.update();
        boolean found1 = true, found2 = true;
        // Aenderungen duerfen nicht uebernommen werden, da sie nicht aktualiesiert werden.
        for(Element element: cache.getAll()){
            if(element.getBezeichnung().equals("CacheTestArtikel12'"))
                found1 = false;
            
            if(element.getBezeichnung().equals("CacheTestArtikel22'"))
                found2 = false;
        }
        assertTrue(found1 & found2);
        if(((Artikel)cache.getById(4242L)).getId_Warentraeger()==null){
            assertTrue("Warentraeger nicht gefunden.",false);
            return;
        }
        
        if(((Artikel)cache.getById(4242L)).getId_Warentraeger()==null){
            assertTrue("Fehlerhafter Warentraeger eintrag.",false);
            return;
        }
    }

    @Override
    public void testUpdateAll() throws DBNotFoundException, QueryException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
