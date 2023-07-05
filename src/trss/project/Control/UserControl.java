/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trss.project.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trss.project.Model.ModelUser;
import static trss.project.ViewControl.User_CL.selectedId;

public class UserControl {
    Statement state;

    public void insert(ModelUser user){
        try {
            PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("INSERT INTO user (username, first_name, last_name, email, role) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getRole());
            statement.executeUpdate();
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modify(ModelUser user){
        try {
            PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("UPDATE user SET username = ?, first_name = ?, last_name = ?, email = ?, role = ? WHERE id = ?");
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getRole());
            statement.setInt(6, selectedId);
            statement.executeUpdate();
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) throws SQLException{
        PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("DELETE FROM user WHERE id = ?");
        statement.setInt(1, selectedId);
        statement.executeUpdate();
        ConnectionDB.closeConnection();
    }

    public ObservableList<ModelUser> getAllUser() {
        ObservableList<ModelUser> userList = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("SELECT * FROM user");
            ResultSet result = statement.executeQuery();
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

                userList.add(obj);
            }
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public ObservableList<ModelUser> searchProduct(String nameS) {
        ObservableList<ModelUser> userList = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("SELECT * FROM user WHERE username LIKE ?");
            statement.setString(1, "%" + nameS + "%");
            ResultSet result = statement.executeQuery();
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

                userList.add(obj);
            }
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }
}
