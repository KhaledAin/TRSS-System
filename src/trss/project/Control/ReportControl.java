/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trss.project.Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportControl {
    private Connection connection;

    public ReportControl() {
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            connection = ConnectionDB.OpenConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    public int getContractCountForMonth(String month) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM contract WHERE MONTH(date) = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, month);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return count;
    }
    
    public int getCustomerCountForMonth(String month) {
    int count = 0;
    String query = "SELECT COUNT(*) FROM customer WHERE MONTH(date) = ?";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, month);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception appropriately
    }

    return count;
}
public double getTotalRevenueForMonth(String month) {
    double totalRevenue = 0.0;
    String query = "SELECT SUM(total_cost) FROM contract WHERE MONTH(date) = ?";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, month);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            totalRevenue = resultSet.getDouble(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception appropriately
    }

    return totalRevenue;
}

public int getProductCountForMonth(String month) {
    int count = 0;
    String query = "SELECT COUNT(*) FROM contractproduct WHERE MONTH(rental_date) = ?";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, month);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception appropriately
    }

    return count;
}

public List<String> getTopRentedProductsForMonth(String month) {
    List<String> topProducts = new ArrayList<>();
    String query = "SELECT p.name " +
                   "FROM contractproduct cp " +
                   "JOIN product p ON cp.product_id = p.id " +
                   "WHERE MONTH(cp.rental_date) = ? " +
                   "GROUP BY p.name " +
                   "ORDER BY COUNT(*) DESC " +
                   "LIMIT 3";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, month);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String productName = resultSet.getString("name");
            topProducts.add(productName);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception appropriately
    }

    return topProducts;
}


    
}
