
package trss.project.Control;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import trss.project.Model.Admin;

/**
 *
 * @author خالد
 */
public class LoginControl {
    Statement st;
    
    public boolean isLogin(Admin ad) throws SQLException{
             

       st = ConnectionDB.OpenConnection().createStatement();
       
       ResultSet  res= st.executeQuery("select * from admins where username ='"+ad.getUsername()+"' and password ='"+ad.getPassword()+"'");
       
       return res.next();
    }
    
}
