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
<<<<<<< HEAD
=======
import java.io.FileWriter;
>>>>>>> ConfigFile
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
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author chrklaas
 */
@ApplicationScoped
public class DatenbankSchnittstelle {

    protected Connection data;                                                        // Datenbank Verbindung
    //-----------------------------------------------------------------------------
    
    private static final boolean DBCONFILE = true; // Server mit Config Datei???
<<<<<<< HEAD
    
    private String DbUrl = "jdbc:mysql://131.173.117.48:3306/df_16115";
    private String DbCd = "com.mysql.jdbc.Driver";
    private String DbUser = "root";
    private String DbPw = "Didpw4df";

    public DatenbankSchnittstelle() throws DBNotFoundException{
        
        if(DBCONFILE){ // Auswerden der Konstanten
           if(!connect("./dbConfig.cfg")){
               throw new DBNotFoundException("DB error...");
           }            
        }else{
            if(!connect(DbUrl,DbCd,DbUser,DbPw))// Oeffnet eine Verbindung mit Standartwerten.
                throw new DBNotFoundException("DB error...");
        }
=======
    private static final String pathToConfig = "./config.cfg";
    
    private String DbUrl = "";
    private String DbCd = "";
    private String DbUser = "";
    private String DbPw = "";

    public DatenbankSchnittstelle() throws DBNotFoundException{
        if(!connect(this.findDBConfigFile())){
            throw new DBNotFoundException("DB error...");
        }            
        
>>>>>>> ConfigFile
    }
    
    public boolean connect(String pathToConfigFile) throws DBNotFoundException{
        File dbConfig = new File(pathToConfigFile);
        if (!dbConfig.exists()) {
            throw new DBNotFoundException("Config file not found (" + pathToConfigFile + ")...");
        } else {
            FileReader dbCReader = null;
            BufferedReader bufferedReader = null;
            try {
                dbCReader = new FileReader(dbConfig);
                bufferedReader = new BufferedReader(dbCReader);
                String input;
                while ((input = bufferedReader.readLine()) != null) { // Liest Config Datei Zeile für Zeile aus und übernimmt die Werte.
<<<<<<< HEAD
                    String line[] = input.split("="); // Kann sein dass nicht beide Zeichen wie gewünscht wegfallen. TESTEN!!!
=======
                    String line[] = input.split("=");
>>>>>>> ConfigFile
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

                if (!connect(DbUrl, DbCd, DbUser, DbPw)) // Oeffnet eine Verbindung mit Werten aus der Config Datei.
                {
                    throw new DBNotFoundException("DB error...");
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
            return data != null;
        } catch (SQLException ex) {
            //Logger.getLogger(DatenbankSchnittstelle.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBNotFoundException("DB not found ("+DbUrl+")...");
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
<<<<<<< HEAD
        Map<String, List<String>> rsMap = new HashMap<>();

        if (data == null) {
            throw new DBNotFoundException("DB error...");
        } else {
            try {
=======
        try {
            Map<String, List<String>> rsMap = new HashMap<>();
            
            if (data == null || !data.isValid(5)) {
                connect(DbUrl, DbCd, DbUser, DbPw);
                //throw new DBNotFoundException("DB error...");
            } else {
>>>>>>> ConfigFile
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
<<<<<<< HEAD
            } catch (SQLException ex) {
                //Logger.getLogger(DatenbankSchnittstelle.class.getName()).log(Level.SEVERE, null, ex);
                throw new QueryException(ex.getMessage());
=======
            }
            return rsMap;
        } catch (SQLException ex) {
            throw new QueryException(ex.getMessage());
        }
    }
    
    public String findDBConfigFile() throws DBNotFoundException{
        String dbConfigFile = "";
        File config = new File(pathToConfig);
        if (!config.exists()) {
            try {
                config.createNewFile();
                try (FileWriter fw = new FileWriter(config)) {
                    fw.append("dbConfigFile=./CONFIGFILE.cfg");
                }
            } catch (IOException ex) {
                Logger.getLogger(DatenbankSchnittstelle.class.getName()).log(Level.SEVERE, null, ex);
            }
            throw new DBNotFoundException("Config file not found (" + pathToConfig + ")...");
        } else {
            FileReader cReader = null;
            BufferedReader bufferedReader = null;
            try {
                cReader = new FileReader(config);
                bufferedReader = new BufferedReader(cReader);
                String input;
                while ((input = bufferedReader.readLine()) != null) { // Liest Config Datei Zeile für Zeile aus und übernimmt die Werte.
                    String line[] = input.split("=");
                    if (line.length > 1) {
                        if (line[0].compareToIgnoreCase("dbConfigFile") == 0) {
                            dbConfigFile = line[1];
                        }
                    } else {
                        throw new DBNotFoundException("Error in config file...");
                    }
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
                    if (cReader != null) {
                        cReader.close();
                    }

                } catch (IOException ex) {
                    throw new DBNotFoundException("Error in file stream...");
                }
>>>>>>> ConfigFile
            }
        }
        
        if(dbConfigFile == null){
            throw new DBNotFoundException("Can not find entry for dbConfig...");
        }
        
        return dbConfigFile;
    }
}
