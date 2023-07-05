/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trss.project.Control;

import trss.project.Model.ModelContract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackerControl {

    private PreparedStatement state;

    // ...

    public List<ModelContract> retrieveContractsFromDatabase() {
        List<ModelContract> contracts = new ArrayList<>();

        try {
            Connection connection = ConnectionDB.OpenConnection();
            String query = "SELECT id, customer_id, total_cost, deposit, date FROM contract";
            state = connection.prepareStatement(query);

            ResultSet resultSet = state.executeQuery();
            while (resultSet.next()) {
                ModelContract contract = new ModelContract();
                contract.setId(resultSet.getInt("id"));
                contract.setCustomer_id(resultSet.getInt("customer_id"));
                contract.setTotal_cost(resultSet.getDouble("total_cost"));
                contract.setDeposit(resultSet.getDouble("deposit"));
                contract.setDate(resultSet.getDate("date"));
                contracts.add(contract);
            }

            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            ex.printStackTrace();
        }

        return contracts;
    }

    // Other methods and database-related code for the TrackerControl class
}
