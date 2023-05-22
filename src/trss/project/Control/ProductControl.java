/* This class use to be managing the database operations for the ModelProduct class
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
import trss.project.Model.ModelProduct;
import static trss.project.ViewControl.Product_CL.selectedId;

/**
 *
 * @author خالد
 */
public class ProductControl {
    
    Statement state;
    
    public void insert(ModelProduct product){
        try{                    
            state = ConnectionDB.OpenConnection().createStatement();
            state.executeUpdate("INSERT INTO product (name, category, price, status) VALUES ('" + product.getName() + "', '" + product.getCategory() + "', " + product.getPrice() + ", '" + product.isStatus() + "')");
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    public void modify(ModelProduct product){
        try{
            state = ConnectionDB.OpenConnection().createStatement();
            state.executeUpdate("update product set name = '"+product.getName()+"', category = '"+product.getCategory()+"', price = "+product.getPrice()+", status = '"+product.isStatus()+"' where id = "+selectedId);
            ConnectionDB.closeConnection();
        }  
        catch(SQLException ex){
             ConnectionDB.closeConnection();
             Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
   
        }
    }
    
    public void delete(int id) throws SQLException{
            state = ConnectionDB.OpenConnection().createStatement();
            state.executeUpdate("delete from product where id = " + selectedId);
            ConnectionDB.closeConnection();
    }
    
    public ObservableList<ModelProduct> getAllProduct() {
        ObservableList product = FXCollections.observableArrayList();
       try{
            state = ConnectionDB.OpenConnection().createStatement();
            ResultSet result = state.executeQuery("select * from product");
            while(result.next()) {
                ModelProduct obj = new ModelProduct();
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    String category = result.getString("category");
                    double price = result.getDouble("price");
                    String status = result.getString("status");
                    
                    obj.setId(id);
                    obj.setName(name);
                    obj.setCategory(category);
                    obj.setPrice(price);
                    obj.setStatus(status);
                    
                    product.add(obj);
                    }
            
       }
       catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
       }
       ConnectionDB.closeConnection();
        return product;
    }
    
    
        public ObservableList<ModelProduct> searchProduct(String nameS) {
        ObservableList product = FXCollections.observableArrayList();
       try{
            state = ConnectionDB.OpenConnection().createStatement();
            ResultSet result = state.executeQuery("select * from product where name like  '%" + nameS + "%'");
            while(result.next()) {
                ModelProduct obj = new ModelProduct();
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    String category = result.getString("category");
                    double price = result.getDouble("price");
                    String status = result.getString("status");
                    
                    obj.setId(id);
                    obj.setName(name);
                    obj.setCategory(category);
                    obj.setPrice(price);
                    obj.setStatus(status);
                    
                    product.add(obj);
                    }
            ConnectionDB.closeConnection();
       }
       catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return product;
    }
    

    
}
