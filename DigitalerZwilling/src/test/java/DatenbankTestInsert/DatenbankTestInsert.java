/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatenbankTestInsert;

import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author florian
 */
@ApplicationScoped
public class DatenbankTestInsert extends DatenbankSchnittstelle{

    public DatenbankTestInsert() throws DBNotFoundException {
        super();
    }

    public void datenbankUpdate(String sqlStatement)  throws DBNotFoundException, QueryException {
        if (data == null){
            throw new DBNotFoundException();
        } else {
            try {
                Statement stmt = data.createStatement();
                stmt.executeUpdate(sqlStatement);
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatenbankTestInsert.class.getName()).log(Level.SEVERE, null, ex);
                throw new QueryException();
            }
            
        }
    }
    
    public void close() throws QueryException{
        if(data != null)
            try {
                data.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatenbankTestInsert.class.getName()).log(Level.SEVERE, null, ex);
            throw new QueryException();
        }
    }
}
