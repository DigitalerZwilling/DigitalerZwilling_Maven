/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.DatenbankSchnittstelle;

import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DB_Exception;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author chrklaas
 */
@ApplicationScoped
public class DatenbankSchnittstelle {

    private Connection data;        // Datenbank Verbindung
    
    @Inject
    @DB_Exception
    Event<Exception> exceptionEvent;
    //-----------------------------------------------------------------------------

    public DatenbankSchnittstelle() throws DBNotFoundException{
         //String DbUrl = "jdbc:derby://localhost:1527/db_DigitalerZwilling";
        String DbUrl = "jdbc:mysql://131.173.117.48:3306/df_16115";
         //String DbCd = "org.apache.derby.jdbc.ClientDriver";
         String DbCd = "com.mysql.jdbc.Driver";
        //String DbUser = "db_user";
        //String DbPw = "SB0222";
        String DbUser = "root";
        String DbPw = "Didpw4df";
        
        try {
            Class.forName(DbCd).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DatenbankSchnittstelle.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.data = DriverManager.getConnection(DbUrl, DbUser, DbPw);
        } catch (SQLException ex) {
            Logger.getLogger(DatenbankSchnittstelle.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBNotFoundException();
            //throw new Exception("Fehler: Datenbankverbindung auf "+ this._DbURL+" nicht möglich");
        }
    }
    
    public DatenbankSchnittstelle(String DbUrl, String DbCd, String DbUser, String DbPw) throws DBNotFoundException {
        try {
            Class.forName(DbCd).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DatenbankSchnittstelle.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.data = DriverManager.getConnection(DbUrl, DbUser, DbPw);
        } catch (SQLException ex) {
            Logger.getLogger(DatenbankSchnittstelle.class.getName()).log(Level.SEVERE, null, ex);
            this.exceptionEvent.fire(new DBNotFoundException(ex.getMessage()));
            throw new DBNotFoundException();
            //throw new Exception("Fehler: Datenbankverbindung auf "+ this._DbURL+" nicht möglich");
        }
    }
    
    /**
     * Die Funktion uebermittelt das Statement an die Datenbank und ruft eine im
     * Parameter "goal" angegeben Funktion parseResult auf in der das ResultSet
     * uebergeben wurde. Anschließend werden ResultSet und Statement
     * geschlossen.
     *
     * @param sqlStatement sql-Anfrage an die Datenbank
     * @return Map die das Result set darstellt
     * @throws de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException
     * @throws de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException
     */
    public Map<String, List<String>> datenbankAnfrage(String sqlStatement) throws DBNotFoundException, QueryException {
        Map<String, List<String>> rsMap = new HashMap<>();
        if (data == null) {
            throw new DBNotFoundException();
        } else {
            try {
                Statement stmt = this.data.createStatement();
                ResultSet rs = stmt.executeQuery(sqlStatement);
                //----------------------------------------------------
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    rsMap.put(rsmd.getColumnName(i).toUpperCase(), new ArrayList<String>());
                }
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        rsMap.get(rsmd.getColumnName(i).toUpperCase()).add(rs.getString(i));
                        
                    }
                }
                //------------------------------------------------------
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatenbankSchnittstelle.class.getName()).log(Level.SEVERE, null, ex);
                throw new QueryException();
            }
        }
        return rsMap;
    }
    
    
}
