package trss.project.ViewControl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import trss.project.Model.ModelContract;
import trss.project.Control.ContractControl;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class Contract_CL implements Initializable {

    @FXML
    private TableView<ModelContract> tableViewContract;
    @FXML
    private TableColumn<ModelContract, Integer> colId;
    @FXML
    private TableColumn<ModelContract, Integer> colUserId;
    @FXML
    private TableColumn<ModelContract, Integer> colCustomerId;
    @FXML
    private TableColumn<ModelContract, Double> colTotalCost;
    @FXML
    private TableColumn<ModelContract, Double> colDeposit;
    @FXML
    private TableColumn<ModelContract, Date> colDate;
    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtCustomerId;
    @FXML
    private TextField txtTotalCost;
    @FXML
    private TextField txtDeposit;
    @FXML
    private DatePicker datePickerDate;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    private ContractControl contractControl;
    private ObservableList<ModelContract> contractList;
    public static int selectedIdContract;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contractControl = new ContractControl();
        contractList = FXCollections.observableArrayList();

        setCellValueFactories();

        // Populate the table with contracts
        contractList.addAll(contractControl.getAllContracts());
        tableViewContract.setItems(contractList);

        // Listen for selection changes and update the selected contract ID
        tableViewContract.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedIdContract = newValue.getId();
            }
        });
    }

    @FXML
private void insertContract(ActionEvent event) {
    try {
        int user_id = Integer.parseInt(txtUserId.getText());
        int customer_id = Integer.parseInt(txtCustomerId.getText());
        double total_cost = Double.parseDouble(txtTotalCost.getText());
        double deposit = Double.parseDouble(txtDeposit.getText());
        Date utilDate = java.sql.Date.valueOf(datePickerDate.getValue());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        ModelContract contract = new ModelContract();
        contract.setUser_id(user_id);
        contract.setCustomer_id(customer_id);
        contract.setTotal_cost(total_cost);
        contract.setDeposit(deposit);
        contract.setDate(sqlDate);

        contractControl.insert(contract);
        contractList.add(contract);

        clearFields();
        showAlert(AlertType.INFORMATION, "Contract Added", "Contract successfully added.");
    } catch (NumberFormatException e) {
        showAlert(AlertType.ERROR, "Invalid Input", "Please enter valid numeric values.");
    }
}

    @FXML
private void updateContract(ActionEvent event) {
    try {
        int user_id = Integer.parseInt(txtUserId.getText());
        int customer_id = Integer.parseInt(txtCustomerId.getText());
        double total_cost = Double.parseDouble(txtTotalCost.getText());
        double deposit = Double.parseDouble(txtDeposit.getText());
        java.sql.Date date = java.sql.Date.valueOf(datePickerDate.getValue());

        ModelContract contract = new ModelContract();
        contract.setId(selectedIdContract);
        contract.setUser_id(user_id);
        contract.setCustomer_id(customer_id);
        contract.setTotal_cost(total_cost);
        contract.setDeposit(deposit);
        contract.setDate(date);

        contractControl.update(contract);

        int selectedIndex = tableViewContract.getSelectionModel().getSelectedIndex();
        contractList.set(selectedIndex, contract);

        clearFields();
        showAlert(AlertType.INFORMATION, "Contract Updated", "Contract successfully updated.");
    } catch (NumberFormatException e) {
        showAlert(AlertType.ERROR, "Invalid Input", "Please enter valid numeric values.");
    }
}

@FXML
private void deleteContract(ActionEvent event) {
    try {
        contractControl.delete(selectedIdContract);
        contractList.removeIf(contract -> contract.getId() == selectedIdContract);

        showAlert(AlertType.INFORMATION, "Contract Deleted", "Contract successfully deleted.");
    } catch (SQLException e) {
        showAlert(AlertType.ERROR, "Deletion Failed", "Failed to delete contract: " + e.getMessage());
    }
}


    private void setCellValueFactories() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("total_cost"));
        colDeposit.setCellValueFactory(new PropertyValueFactory<>("deposit"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void clearFields() {
        txtUserId.clear();
        txtCustomerId.clear();
        txtTotalCost.clear();
        txtDeposit.clear();
        datePickerDate.setValue(null);
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

