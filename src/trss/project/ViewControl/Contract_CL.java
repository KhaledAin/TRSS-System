package trss.project.ViewControl;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import trss.project.Control.ContractControl;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import trss.project.Control.ConnectionDB;

public class Contract_CL implements Initializable {

    @FXML
    private ComboBox<String> CustomerCombo;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField txtID;
    @FXML
    private ComboBox<String> ProductNameCombo;
    @FXML
    private TextField productidtxt;
    @FXML
    private TextField costtxt;
    @FXML
    private TextField totalitemtxt;
    @FXML
    private TextField totalitemtxt4;
    @FXML
    private TextField totalitemtxt3;
    @FXML
    private TextField totalitemtxt5;
    @FXML
    private TextField totalitemtxt51;
    @FXML
    private TextField totaltxt;
    @FXML
    private TextField periodtxt;
    @FXML
    private ComboBox<String> ProductNameCombo1;
    @FXML
    private ComboBox<String> ProductNameCombo3;
    @FXML
    private ComboBox<String> ProductNameCombo4;
    @FXML
    private ComboBox<String> ProductNameCombo5; 
    @FXML
    private TextField productidtxt1;
    @FXML
    private TextField costtxt1;
    @FXML
    private TextField totalitemtxt1;
    @FXML
    private TextField periodtxt1;
    @FXML
    private ComboBox<String> ProductNameCombo2;
    @FXML
    private TextField productidtxt2;
    @FXML
    private TextField costtxt2;
    @FXML
    private TextField totalitemtxt2;
    @FXML
    private TextField periodtxt2;
    @FXML
    private TextField deposittxt;
    @FXML
    private TextField balancetxt;

    private ContractControl contractControl;
    @FXML
    private TextField periodtxt3;
    @FXML
    private TextField costtxt3;
    @FXML
    private TextField periodtxt4;
    @FXML
    private TextField costtxt4;
    @FXML
    private TextField periodtxt5;
    @FXML
    private TextField costtxt5;
    @FXML
    private TextField productidtxt3;
    @FXML
    private TextField productidtxt4;
    @FXML
    private TextField productidtxt5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contractControl = new ContractControl();
        populateCustomerCombo();
        populateProductNameCombo();
        setNextContractID();

        // Add selection change listener to ProductNameCombo
        ProductNameCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String productId = contractControl.getProductIdByName(newValue.split("\\(")[0].trim());
                productidtxt.setText(productId);

                String cost = contractControl.getProductCostByName(newValue.split("\\(")[0].trim());
                costtxt.setText(cost);

                calculateTotalItemCost();
            }
        });

        // Add change listener to periodtxt
        periodtxt.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateTotalItemCost();
        });

        // Add selection change listener to ProductNameCombo1
        ProductNameCombo1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String productId = contractControl.getProductIdByName(newValue.split("\\(")[0].trim());
                productidtxt1.setText(productId);

                String cost = contractControl.getProductCostByName(newValue.split("\\(")[0].trim());
                costtxt1.setText(cost);

                calculateTotalItemCost1();
            }
        });

        // Add change listener to periodtxt1
        periodtxt1.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateTotalItemCost1();
        });

        // Add selection change listener to ProductNameCombo2
        ProductNameCombo2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String productId = contractControl.getProductIdByName(newValue.split("\\(")[0].trim());
                productidtxt2.setText(productId);

                String cost = contractControl.getProductCostByName(newValue.split("\\(")[0].trim());
                costtxt2.setText(cost);

                calculateTotalItemCost2();
            }
        });

        // Add change listener to periodtxt2
        periodtxt2.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateTotalItemCost2();
        });
        
            totalitemtxt.textProperty().addListener((observable, oldValue, newValue) -> {
        calculateTotal();
    });

        totalitemtxt1.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateTotal();
        });

        totalitemtxt2.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateTotal();
        });

        totalitemtxt3.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateTotal();
        });

        totalitemtxt4.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateTotal();
        });

    totalitemtxt5.textProperty().addListener((observable, oldValue, newValue) -> {
        calculateTotal();
        
        
    });
    ProductNameCombo3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            String productId = contractControl.getProductIdByName(newValue.split("\\(")[0].trim());
            productidtxt3.setText(productId);

            String cost = contractControl.getProductCostByName(newValue.split("\\(")[0].trim());
            costtxt3.setText(cost);

            calculateTotalItemCost3();
            calculateTotal();
        }
    });

    // Add change listener to periodtxt3
    periodtxt3.textProperty().addListener((observable, oldValue, newValue) -> {
        calculateTotalItemCost3();
        calculateTotal();
    });

    // Add selection change listener to ProductNameCombo4
    ProductNameCombo4.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            String productId = contractControl.getProductIdByName(newValue.split("\\(")[0].trim());
            productidtxt4.setText(productId);

            String cost = contractControl.getProductCostByName(newValue.split("\\(")[0].trim());
            costtxt4.setText(cost);

            calculateTotalItemCost4();
            calculateTotal();
        }
    });

    // Add change listener to periodtxt4
    periodtxt4.textProperty().addListener((observable, oldValue, newValue) -> {
        calculateTotalItemCost4();
        calculateTotal();
    });

    // Add selection change listener to ProductNameCombo5
    ProductNameCombo5.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            String productId = contractControl.getProductIdByName(newValue.split("\\(")[0].trim());
            productidtxt5.setText(productId);

            String cost = contractControl.getProductCostByName(newValue.split("\\(")[0].trim());
            costtxt5.setText(cost);

            calculateTotalItemCost5();
            calculateTotal();
        }
    });

    // Add change listener to periodtxt5
    periodtxt5.textProperty().addListener((observable, oldValue, newValue) -> {
        calculateTotalItemCost5();
        calculateTotal();
    });
        
        deposittxt.textProperty().addListener((observable, oldValue, newValue) -> calculateBalance());

        
    }
    

    private void calculateTotalItemCost() {
        try {
            int quantity = Integer.parseInt(periodtxt.getText().trim());
            double cost = Double.parseDouble(costtxt.getText().trim());
            double totalCost = quantity * cost;
            totalitemtxt.setText(String.valueOf(totalCost));
        } catch (NumberFormatException e) {
            totalitemtxt.setText("");
        }
    }

    private void calculateTotalItemCost1() {
        try {
            int quantity = Integer.parseInt(periodtxt1.getText().trim());
            double cost = Double.parseDouble(costtxt1.getText().trim());
            double totalCost = quantity * cost;
            totalitemtxt1.setText(String.valueOf(totalCost));
        } catch (NumberFormatException e) {
            totalitemtxt1.setText("");
        }
    }

    private void calculateTotalItemCost2() {
        try {
            int quantity = Integer.parseInt(periodtxt2.getText().trim());
            double cost = Double.parseDouble(costtxt2.getText().trim());
            double totalCost = quantity * cost;
            totalitemtxt2.setText(String.valueOf(totalCost));
        } catch (NumberFormatException e) {
            totalitemtxt2.setText("");
        }
    }
    
    private void calculateTotalItemCost3() {
    try {
        int quantity = Integer.parseInt(periodtxt3.getText().trim());
        double cost = Double.parseDouble(costtxt3.getText().trim());
        double totalCost = quantity * cost;
        totalitemtxt3.setText(String.valueOf(totalCost));
    } catch (NumberFormatException e) {
        totalitemtxt3.setText("");
    }
}

private void calculateTotalItemCost4() {
    try {
        int quantity = Integer.parseInt(periodtxt4.getText().trim());
        double cost = Double.parseDouble(costtxt4.getText().trim());
        double totalCost = quantity * cost;
        totalitemtxt4.setText(String.valueOf(totalCost));
    } catch (NumberFormatException e) {
        totalitemtxt4.setText("");
    }
}

private void calculateTotalItemCost5() {
    try {
        int quantity = Integer.parseInt(periodtxt5.getText().trim());
        double cost = Double.parseDouble(costtxt5.getText().trim());
        double totalCost = quantity * cost;
        totalitemtxt5.setText(String.valueOf(totalCost));
    } catch (NumberFormatException e) {
        totalitemtxt5.setText("");
    }
}
    
    

    private void populateCustomerCombo() {
        CustomerCombo.getItems().addAll(contractControl.getAllCustomerNames());
    }

    private void populateProductNameCombo() {
        ProductNameCombo.getItems().addAll(contractControl.getAllProductNames());
        ProductNameCombo1.getItems().addAll(contractControl.getAllProductNames());
        ProductNameCombo2.getItems().addAll(contractControl.getAllProductNames());
        ProductNameCombo3.getItems().addAll(contractControl.getAllProductNames());
        ProductNameCombo4.getItems().addAll(contractControl.getAllProductNames());
        ProductNameCombo5.getItems().addAll(contractControl.getAllProductNames());
    }

    private void setNextContractID() {
        String nextID = contractControl.getNextContractID();
        txtID.setText(nextID);
    }
    
    
    private void calculateTotal() {
    double total = 0.0;
    total += parseDouble(totalitemtxt.getText());
    total += parseDouble(totalitemtxt1.getText());
    total += parseDouble(totalitemtxt2.getText());
    total += parseDouble(totalitemtxt3.getText());
    total += parseDouble(totalitemtxt4.getText());
    total += parseDouble(totalitemtxt5.getText());
    total += parseDouble(totalitemtxt51.getText());

    totaltxt.setText(Double.toString(total));
}

private double parseDouble(String text) {
    try {
        return Double.parseDouble(text);
    } catch (NumberFormatException e) {
        return 0.0;
    }
}
public void calculateBalance() {
    try {
        // Parse the deposit and total values as doubles
        double deposit = Double.parseDouble(deposittxt.getText());
        double total = Double.parseDouble(totaltxt.getText());

        double balance = total - deposit;

        // Set the calculated balance value in the balancetxt TextField
        balancetxt.setText(String.valueOf(balance));
    } catch (NumberFormatException e) {
        // Handle the error when parsing fails
        // Display an error message or perform appropriate error handling
        System.out.println("Invalid input: Please enter valid numeric values for deposit and total.");
    }
}



        
    public void backHome(Event e) throws IOException{
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        // stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/trss/project/View/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    
    
    
    
    public void saveContractToDatabase() {
    

    String id = txtID.getText();
    String userId = "1"; // Replace with the user ID logic
    String customerName = CustomerCombo.getValue();
    String customerId = contractControl.getCustomerIdByName(customerName); // Fetch customer ID based on customer name
    Date date = Date.valueOf(txtDate.getValue());
    double totalCost = Double.parseDouble(totaltxt.getText());
    double deposit = Double.parseDouble(deposittxt.getText());

    Connection connection = null;
    PreparedStatement statement = null;
    
    
    try {
        connection = ConnectionDB.OpenConnection();
        String query = "INSERT INTO contract (id, user_id, customer_id, date, total_cost, deposit) VALUES (?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, userId);
        statement.setString(3, customerId);
        statement.setDate(4, date);
        statement.setDouble(5, totalCost);
        statement.setDouble(6, deposit);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            
            System.out.println("Contract saved to the database successfully.");
             
             clearFields();
        } else {
            System.out.println("Failed to save the contract to the database.");
        }
    } catch (SQLException e) {
        System.out.println("Error while saving the contract to the database: " + e.getMessage());
    } finally {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error while closing the statement: " + e.getMessage());
            }
        }
        ConnectionDB.closeConnection();
    }
    
    

}

private void clearFields() {
    try {
        String currentID = txtID.getText();
        int incrementedID = Integer.parseInt(currentID) + 1;
        txtID.setText(String.valueOf(incrementedID));
        
        txtDate.setValue(null);
        totaltxt.clear();
        deposittxt.clear();
        ProductNameCombo.getSelectionModel().clearSelection();
        costtxt.clear();
        CustomerCombo.getSelectionModel().clearSelection();
        productidtxt.clear();
        productidtxt1.clear();
        productidtxt2.clear();
        productidtxt3.clear(); // Clear productidtxt3
        productidtxt4.clear(); // Clear productidtxt4
        productidtxt5.clear(); // Clear productidtxt5
        ProductNameCombo.getSelectionModel().clearSelection();
        ProductNameCombo1.getSelectionModel().clearSelection();
        ProductNameCombo2.getSelectionModel().clearSelection();
        ProductNameCombo3.getSelectionModel().clearSelection(); // Clear ProductNameCombo3
        ProductNameCombo4.getSelectionModel().clearSelection(); // Clear ProductNameCombo4
        ProductNameCombo5.getSelectionModel().clearSelection(); // Clear ProductNameCombo5
        costtxt1.clear();
        costtxt2.clear();
        costtxt3.clear(); // Clear costtxt3
        costtxt4.clear(); // Clear costtxt4
        costtxt5.clear(); // Clear costtxt5
        periodtxt.clear();
        periodtxt1.clear();
        periodtxt2.clear();
        periodtxt3.clear(); // Clear periodtxt3
        periodtxt4.clear(); // Clear periodtxt4
        periodtxt5.clear(); // Clear periodtxt5
        totalitemtxt.clear();
        totalitemtxt1.clear();
        totalitemtxt2.clear();
        totalitemtxt3.clear(); // Clear totalitemtxt3
        totalitemtxt4.clear(); // Clear totalitemtxt4
        totalitemtxt5.clear(); // Clear totalitemtxt5
        
        balancetxt.clear();
        totaltxt.clear();
    } catch (NumberFormatException e) {
        // Handle the exception or display an error message
        e.printStackTrace();
    }
}


    
    
}



