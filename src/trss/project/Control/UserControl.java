/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trss.project.Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trss.project.Model.ModelUser;
import static trss.project.ViewControl.User_CL.selectedId;

/**
 *
 * @author خالد
 */
public class UserControl {
    Statement state;

    
    public void insert(ModelUser user){
        try{                    
            state = ConnectionDB.OpenConnection().createStatement();
            state.executeUpdate("INSERT INTO user (username,first_name,last_name,email,role) VALUES ('" + user.getUserName()+ "', '" + user.getFirstName()+ "','" + user.getLastName()+ "', '" + user.getEmail()+ "', '" + user.getRole()+"')");
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    
    public void modify(ModelUser user){
        try{
            state = ConnectionDB.OpenConnection().createStatement();
            state.executeUpdate("update user set username = '"+user.getUserName()+"', first_name = '"+user.getFirstName()+"', last_name = '"+user.getLastName()+"', email = '"+user.getEmail()+"', role = '"+user.getRole()+"' where id = "+selectedId);
            ConnectionDB.closeConnection();
        }  
        catch(SQLException ex){
             ConnectionDB.closeConnection();
             Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
   
        }
    }
    
        public void delete(int id) throws SQLException{
            state = ConnectionDB.OpenConnection().createStatement();
            state.executeUpdate("delete from user where id = " + selectedId);
            ConnectionDB.closeConnection();
    }
    
    public ObservableList<ModelUser> getAllUser() {
        ObservableList<ModelUser> user = FXCollections.observableArrayList();
       try{
            state = ConnectionDB.OpenConnection().createStatement();
            ResultSet result = state.executeQuery("select * from user");
            while(result.next()) {
                ModelUser obj = new ModelUser();
                    int id = result.getInt("id");
                    String username = result.getString("username");
                    String first_name = result.getString("first_name");
                    String last_name = result.getString("last_name");
                    String email = result.getString("email");
                    String role = result.getString("role");
                    
                    obj.setId(id);
                    obj.setUserName(username);
                    obj.setFirstName(first_name);
                    obj.setLastName(last_name);
                    obj.setEmail(email);
                    obj.setRole(role);
                    
                    user.add(obj);
                    
                   
                    }
            
       }
       catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
       }
       ConnectionDB.closeConnection();
       
        return user;
    }
    
    
    
        public ObservableList<ModelUser> searchProduct(String nameS) {
            ObservableList user = FXCollections.observableArrayList();
           try{
                state = ConnectionDB.OpenConnection().createStatement();
                ResultSet result = state.executeQuery("select * from user where username like  '%" + nameS + "%'");
                while(result.next()) {
                    ModelUser obj = new ModelUser();
                        int id = result.getInt("id");
                        String username = result.getString("username");
                        String first_name = result.getString("first_name");
                        String last_name = result.getString("last_name");
                        String email = result.getString("email");
                        String role = result.getString("role");

                        obj.setId(id);
                        obj.setUserName(username);
                        obj.setFirstName(first_name);
                        obj.setLastName(last_name);
                        obj.setEmail(email);
                        obj.setEmail(role);

                        user.add(obj);
                        }
                ConnectionDB.closeConnection();
       }
       catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return user;
    }
    

    
}
