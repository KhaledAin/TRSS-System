package trss.project.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trss.project.Model.ModelProduct;
import static trss.project.ViewControl.Product_CL.selectedId;

public class ProductControl {
    
    Statement state;
    
    public void insert(ModelProduct product){
        try {
            PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("INSERT INTO product (name, category, price, status) VALUES (?, ?, ?, ?)");
            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, String.valueOf(product.isStatus()));
            statement.executeUpdate();
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modify(ModelProduct product){
        try {
            PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("UPDATE product SET name = ?, category = ?, price = ?, status = ? WHERE id = ?");
            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, String.valueOf(product.isStatus()));
            statement.setInt(5, selectedId);
            statement.executeUpdate();
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id) throws SQLException {
        PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("DELETE FROM product WHERE id = ?");
        statement.setInt(1, selectedId);
        statement.executeUpdate();
        ConnectionDB.closeConnection();
    }
    
    public ObservableList<ModelProduct> getAllProduct() {
        ObservableList<ModelProduct> productList = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("SELECT * FROM product");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
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
                    
                productList.add(obj);
            }
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }
    
    public ObservableList<ModelProduct> searchProduct(String nameS) {
        ObservableList<ModelProduct> productList = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("SELECT * FROM product WHERE name LIKE ?");
            statement.setString(1, "%" + nameS + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
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

                    productList.add(obj);
                    }
            ConnectionDB.closeConnection();
       }
       catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return productList;
    }
    

    
}
