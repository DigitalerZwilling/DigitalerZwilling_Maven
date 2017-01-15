/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatenbankTestInsert;

import de.hsos.digitalerzwilling.DatenbankSchnittstelle.DatenbankSchnittstelle;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.DBNotFoundException;
import de.hsos.digitalerzwilling.DatenbankSchnittstelle.Exception.QueryException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        String DbUrl = "jdbc:mysql://131.173.117.48:3306/df_16115";
        String DbCd = "com.mysql.jdbc.Driver";
        String DbUser = "testroot";
        String DbPw = "testDidpw4df";
        
        File dbConfig = new File("./dbConfig.cfg");
        if (!dbConfig.exists()) {
            throw new DBNotFoundException("Config file not found...");
        } else {
            FileReader dbCReader = null;
            BufferedReader bufferedReader = null;
            try {
                dbCReader = new FileReader(dbConfig);
                bufferedReader = new BufferedReader(dbCReader);
                String input;
                while ((input = bufferedReader.readLine()) != null) { // Liest Config Datei Zeile für Zeile aus und übernimmt die Werte.
                    String line[] = input.split("= "); // Kann sein dass nicht beide Zeichen wie gewünscht wegfallen. TESTEN!!!
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
