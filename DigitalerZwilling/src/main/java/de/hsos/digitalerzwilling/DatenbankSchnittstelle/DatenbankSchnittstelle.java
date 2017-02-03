/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.digitalerzwilling.DatenbankSchnittstelle;

import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

/**
 *
 * @author chrklaas
 */
@ApplicationScoped
public class DatenbankSchnittstelle {

    protected Connection data;                                                        // Datenbank Verbindung
    //-----------------------------------------------------------------------------
    
    private static final String pathToConfig = "./DZConfig.cfg";
    
    private String DbUrl = "";
    private String DbCd = "";
    private String DbUser = "";
    private String DbPw = "";

    public DatenbankSchnittstelle() throws DBNotFoundException{
        if(!connect(pathToConfig)){
            throw new DBNotFoundException("DB error...");
        }            
        

    }
    
    public boolean connect(String pathToConfigFile) throws DBNotFoundException{
        File dbConfig = new File(pathToConfigFile);
        if (!dbConfig.exists()) {
            try {
                dbConfig.createNewFile();
            } catch (IOException ex) {
                throw new DBNotFoundException("Config file not found (" + pathToConfigFile + ") and can not be create...");
            }
            throw new DBNotFoundException("Config file not found (" + pathToConfigFile + ")...");
        } else {
            FileReader dbCReader = null;
            BufferedReader bufferedReader = null;
            try {
                dbCReader = new FileReader(dbConfig);
                bufferedReader = new BufferedReader(dbCReader);
                String input;
                while ((input = bufferedReader.readLine()) != null) { // Liest Config Datei Zeile für Zeile aus und übernimmt die Werte.

                    String line[] = input.split("=");
                    if (line.length > 1) {
                        if (line[0].compareToIgnoreCase("DbUrl") == 0) {
                            DbUrl = line[1];
                        } else if (line[0].compareToIgnoreCase("DbCd") == 0) {
                            DbCd = line[1];
                        } else if (line[0].compareToIgnoreCase("DbUser") == 0) {
                            DbUser = line[1];
                        } else if (line[0].compareToIgnoreCase("DbPw") == 0) {
                            DbPw = line[1];
                        }
                    } else {
                        throw new DBNotFoundException("Error in config file...");
                    }
                }
                try{
                    if (!connect(DbUrl, DbCd, DbUser, DbPw)) // Oeffnet eine Verbindung mit Werten aus der Config Datei.
                    {
                        throw new DBNotFoundException("DB error...");
                    }
                }catch(DBNotFoundException ex){
                    Logger.getLogger(DatenbankSchnittstelle.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (FileNotFoundException ex) {
                throw new DBNotFoundException("Config file not found...");
            } catch (IOException ex) {
                throw new DBNotFoundException("Error in file stream...");
            } finally {
                try {

                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (dbCReader != null) {
                        dbCReader.close();
                    }

                } catch (IOException ex) {
                    throw new DBNotFoundException("Error in file stream...");
                }
            }
        }
        
        return data != null;
    }
    
    public boolean connect(String DbUrl, String DbCd, String DbUser, String DbPw) throws DBNotFoundException {
        try {
            Class.forName(DbCd).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new DBNotFoundException("Driver not found ("+DbCd+")...");
        }
        try {
            if(data != null){
                data.close();
                data = null;
            }
            this.data = DriverManager.getConnection(DbUrl, DbUser, DbPw);
            if(data==null)
                return false;
            else
                this.data.setAutoCommit(false);
                return data.isValid(10);
        } catch (SQLException ex) {
            //Logger.getLogger(DatenbankSchnittstelle.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBNotFoundException("DB not found ("+DbUrl+")... cause SQLException: "+ex.getMessage());
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
        try {
            Map<String, List<String>> rsMap = new HashMap<>();
            
            if (data == null || !data.isValid(5)) {
                connect(DbUrl, DbCd, DbUser, DbPw);
                //throw new DBNotFoundException("DB error...");
            } else {
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
            }
            return rsMap;
        } catch (SQLException ex) {
            throw new QueryException(ex.getMessage());
        }
    }
    
    public void commitAndBegin(){
        try {
            if(this.data.getAutoCommit()==false){
                Statement stmt = this.data.createStatement();
                stmt.execute("COMMIT");
                stmt.execute("BEGIN");
                stmt.close();
            }else{
                Logger.getLogger(DatenbankSchnittstelle.class.getName()).log(Level.INFO, "AUTO_COMMIT der Datenbank ist aktiviert");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatenbankSchnittstelle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
