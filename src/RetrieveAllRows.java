import java.sql.*;
import trss.project.Model.Admin;
import trss.project.Control.LoginControl;
public class RetrieveAllRows {
    public static void main(String[] args) throws SQLException {
        Admin ad = new Admin();
        ad.setUsername("admin");
        ad.setPassword("");
        LoginControl cl = new LoginControl();
       
       System.out.print( cl.isLogin(ad));
        
        
        
        
        
        /*
        
    
        String url = "jdbc:mysql://localhost:3306/db";
        String user = "root";
        String password = "khaled7229793";
        String sql = "SELECT * FROM admins";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String username = rs.getString("username");
                String passwordd = rs.getString("password");
                String type = rs.getString("type");
                
                System.out.println("Username: " + username + ", Password: " + passwordd + ", Type: " + type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
    }
}
