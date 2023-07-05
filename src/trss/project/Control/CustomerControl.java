/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trss.project.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trss.project.Model.ModelCustomer;
import static trss.project.ViewControl.Customer_CL.selectedIdCustomer;

/**
 *
 * @author خالد
 */
public class CustomerControl {
        PreparedStatement state;

    public void insert(ModelCustomer customer) {
        try {
            state = ConnectionDB.OpenConnection().prepareStatement("INSERT INTO customer (name, nic, address, phone) VALUES (?, ?, ?, ?)");
            state.setString(1, customer.getName());
            state.setString(2, customer.getNic());
            state.setString(3, customer.getAdress());
            state.setString(4, customer.getPhone());
            state.executeUpdate();
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


public void modify(ModelCustomer customer) {
    try {
        state = ConnectionDB.OpenConnection().prepareStatement("update customer set name = ?, nic = ?, address = ?, phone = ? where id = ?");
        state.setString(1, customer.getName());
        state.setString(2, customer.getNic());
        state.setString(3, customer.getAdress());
        state.setString(4, customer.getPhone());
        state.setInt(5, selectedIdCustomer);
        state.executeUpdate();
        ConnectionDB.closeConnection();
    } catch (SQLException ex) {
        ConnectionDB.closeConnection();
        Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public void delete(int id) throws SQLException {
    try {
        state = ConnectionDB.OpenConnection().prepareStatement("delete from customer where id = ?");
        state.setInt(1, selectedIdCustomer);
        state.executeUpdate();
        ConnectionDB.closeConnection();
    } catch (SQLException ex) {
        ConnectionDB.closeConnection();
        Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public ObservableList<ModelCustomer> getAllCustomers() {
    ObservableList<ModelCustomer> customerList = FXCollections.observableArrayList();
    try {
        PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("SELECT * FROM customer");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            ModelCustomer obj = new ModelCustomer();
            int id = result.getInt("id");
            String name = result.getString("name");
            String nic = result.getString("NIC");
            String address = result.getString("address");
            String phone = result.getString("phone");

            obj.setId(id);
            obj.setName(name);
            obj.setNic(nic);
            obj.setAdress(address);
            obj.setPhone(phone);

            customerList.add(obj);
        }
        ConnectionDB.closeConnection();
    } catch (SQLException ex) {
        ConnectionDB.closeConnection();
        Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return customerList;
}



public ObservableList<ModelCustomer> searchCustomer(String nameS) {
    ObservableList<ModelCustomer> customerList = FXCollections.observableArrayList();
    try {
        state = ConnectionDB.OpenConnection().prepareStatement("select * from customer where name like ?");
        state.setString(1, "%" + nameS + "%");
        ResultSet result = state.executeQuery();
        while (result.next()) {
            ModelCustomer obj = new ModelCustomer();
            int id = result.getInt("id");
            String name = result.getString("name");
            String nic = result.getString("NIC");
            String address = result.getString("address");
            String phone = result.getString("phone");

            obj.setId(id);
            obj.setName(name);
            obj.setNic(nic);
            obj.setAdress(address);
            obj.setPhone(phone);

            customerList.add(obj);
        }
        ConnectionDB.closeConnection();
    } catch (SQLException ex) {
        ConnectionDB.closeConnection();
        Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return customerList;
}

    
}

    
    

