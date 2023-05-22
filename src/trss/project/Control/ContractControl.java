package trss.project.Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trss.project.Model.ModelContract;
import static trss.project.ViewControl.Contract_CL.selectedIdContract;

public class ContractControl {
    private Statement state;
    
    public void insert(ModelContract contract) {
        try {                    
            state = ConnectionDB.OpenConnection().createStatement();
            state.executeUpdate("INSERT INTO contract (user_id, customer_id, total_cost, deposit, date) VALUES (" + contract.getUser_id() + ", " + contract.getCustomer_id() + ", " + contract.getTotal_cost() + ", " + contract.getDeposit() + ", '" + contract.getDate() + "')");
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ContractControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(ModelContract contract) {
        try {
            state = ConnectionDB.OpenConnection().createStatement();
            state.executeUpdate("UPDATE contract SET user_id = " + contract.getUser_id() + ", customer_id = " + contract.getCustomer_id() + ", total_cost = " + contract.getTotal_cost() + ", deposit = " + contract.getDeposit() + ", date = '" + contract.getDate() + "' WHERE id = " + selectedIdContract);
            ConnectionDB.closeConnection();
        } catch(SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ContractControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id) throws SQLException {
        state = ConnectionDB.OpenConnection().createStatement();
        state.executeUpdate("DELETE FROM contract WHERE id = " + selectedIdContract);
        ConnectionDB.closeConnection();
    }
    
    public ObservableList<ModelContract> getAllContracts() {
        ObservableList<ModelContract> contracts = FXCollections.observableArrayList();
        try {
            state = ConnectionDB.OpenConnection().createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM contract");
            while(result.next()) {
                ModelContract obj = new ModelContract();
                int id = result.getInt("id");
                int user_id = result.getInt("user_id");
                int customer_id = result.getInt("customer_id");
                double total_cost = result.getDouble("total_cost");
                double deposit = result.getDouble("deposit");
                java.sql.Date date = result.getDate("date");
                
                obj.setId(id);
                obj.setUser_id(user_id);
                obj.setCustomer_id(customer_id);
                obj.setTotal_cost(total_cost);
                obj.setDeposit(deposit);
                obj.setDate(date);
                
                contracts.add(obj);
            }
            
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ContractControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionDB.closeConnection();
        
        return contracts;
    }
}
