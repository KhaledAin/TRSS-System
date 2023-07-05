/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trss.project.Control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author خالد
 */
public class ContractControl {
     private PreparedStatement state;
    
    public List<String> getAllCustomerNames() {
    List<String> customerNames = new ArrayList<>();
    try {
        PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("SELECT name FROM customer");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String name = result.getString("name");
            customerNames.add(name);
        }
        ConnectionDB.closeConnection();
    } catch (SQLException ex) {
        ConnectionDB.closeConnection();
        Logger.getLogger(ContractControl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return customerNames;
}
public String getNextContractID() {
    String nextID = null;
    try {
        PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("SELECT MAX(id) as max_id FROM contract");
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            int maxID = result.getInt("max_id");
            nextID = String.valueOf(maxID + 1);
        }
        ConnectionDB.closeConnection();
    } catch (SQLException ex) {
        ConnectionDB.closeConnection();
        Logger.getLogger(ContractControl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return nextID;
}
 public List<String> getAllProductNames() {
        List<String> productNames = new ArrayList<>();
        try {
            PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("SELECT name FROM product");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String name = result.getString("name");
                productNames.add(name);
            }
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ContractControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productNames;
    }
     public List<String> getAllProductNamesWithCategory() {
        List<String> productNamesWithCategory = new ArrayList<>();
        try {
            PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("SELECT name, category FROM product");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String name = result.getString("name");
                String category = result.getString("category");
                String productNameWithCategory = name + " (" + category + ")";
                productNamesWithCategory.add(productNameWithCategory);
            }
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ContractControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productNamesWithCategory;
    }
public String getProductIdByName(String productName) {
    String productId = null;
    try {
        PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("SELECT id FROM product WHERE name = ?");
        statement.setString(1, productName);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            productId = result.getString("id");
        }
        ConnectionDB.closeConnection();
    } catch (SQLException ex) {
        ConnectionDB.closeConnection();
        Logger.getLogger(ContractControl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return productId;
}
public String getProductCostByName(String productName) {
    String productCost = null;
    try {
        PreparedStatement statement = ConnectionDB.OpenConnection().prepareStatement("SELECT price FROM product WHERE name = ?");
        statement.setString(1, productName);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            productCost = result.getString("price");
        }
        ConnectionDB.closeConnection();
    } catch (SQLException ex) {
        ConnectionDB.closeConnection();
        Logger.getLogger(ContractControl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return productCost;
}

  public String getCustomerIdByName(String customerName) {
        String customerId = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDB.OpenConnection();
            String query = "SELECT id FROM customer WHERE name = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, customerName);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                customerId = resultSet.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving customer ID from the database: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("Error while closing the result set: " + e.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error while closing the statement: " + e.getMessage());
                }
            }
            ConnectionDB.closeConnection();
        }

        return customerId;
    }
  
  public void saveContractProduct(int productId, String productName, int period, int contractId) {
        try {
            Connection connection = ConnectionDB.OpenConnection();
            String query = "INSERT INTO contractproduct (productid, productname, period, contractid) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            statement.setString(2, productName);
            statement.setInt(3, period);
            statement.setInt(4, contractId);
            statement.executeUpdate();
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ContractControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
   
}




    

