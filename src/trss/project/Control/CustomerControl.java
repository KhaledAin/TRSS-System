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
import trss.project.Model.ModelCustomer;
import static trss.project.ViewControl.Customer_CL.selectedIdCustomer;

/**
 *
 * @author خالد
 */
public class CustomerControl {
    Statement state;
    
    public void insert(ModelCustomer customer){
        try{                    
            state = ConnectionDB.OpenConnection().createStatement();
            state.executeUpdate("INSERT INTO customer (name, nic , address, phone) VALUES ('" + customer.getName() + "', '" + customer.getNic()+ "', '" + customer.getAdress()+ "' , '" + customer.getPhone()+ "')");
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    public void modify(ModelCustomer customer){
        try{
            state = ConnectionDB.OpenConnection().createStatement();
            state.executeUpdate("update customer set name = '" + customer.getName() + "', nic = '" + customer.getNic() + "', address = '" + customer.getAdress() + "', phone = '" + customer.getPhone() + "' where id = " + selectedIdCustomer);
            ConnectionDB.closeConnection();
        }  
        catch(SQLException ex){
             ConnectionDB.closeConnection();
             Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
   
        }
    }
    
    public void delete(int id) throws SQLException{
            state = ConnectionDB.OpenConnection().createStatement();
            state.executeUpdate("delete from customer where id = " + selectedIdCustomer);
            ConnectionDB.closeConnection();
    }
    
    public ObservableList<ModelCustomer> getAllCustomers() {
        ObservableList customer = FXCollections.observableArrayList();
       try{
            state = ConnectionDB.OpenConnection().createStatement();
            ResultSet result = state.executeQuery("select * from customer");
            while(result.next()) {
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
                    
                    customer.add(obj);
                    }
            
       }
       catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
       }
       ConnectionDB.closeConnection();
        return customer;
    }
    
    
        public ObservableList<ModelCustomer> searchCustomer(String nameS) {
        ObservableList customer = FXCollections.observableArrayList();
       try{
            state = ConnectionDB.OpenConnection().createStatement();
            ResultSet result = state.executeQuery("select * from customer where name like  '%" + nameS + "%'");
            while(result.next()) {
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
                    
                    customer.add(obj);
                    }
            ConnectionDB.closeConnection();
       }
       catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return customer;
    }
    
    
}

    
    

