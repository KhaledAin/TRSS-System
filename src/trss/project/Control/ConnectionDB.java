
package trss.project.Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author خالد
 */
public class ConnectionDB {
    
    private static Connection  con = null;
    
        private ConnectionDB(){
        
    }
    
    public static Connection OpenConnection() throws SQLException{
        if(con== null)
            con = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost/db","root","khaled7229793");
        return con;
  
    }
    
    public static void closeConnection(){
        if(con != null)
            con = null;
    }

    
}
