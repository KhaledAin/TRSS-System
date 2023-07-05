
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
       
       ResultSet  res= st.executeQuery("select * from user where username ='"+ad.getUsername()+"' and password ='"+ad.getPassword()+"'");
       
       return res.next();
    }
    
    
        public String getUserRole(String username) throws SQLException {
        st = ConnectionDB.OpenConnection().createStatement();

        String query = "SELECT role FROM user WHERE username = '" + username + "'";
        ResultSet result = st.executeQuery(query);

        if (result.next()) {
            return result.getString("role");
        }

        return null; // Return null or handle the case when the user role is not found
    }
    
}
