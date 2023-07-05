package trss.project.ViewControl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import trss.project.Control.ContractControl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class test implements Initializable {

    @FXML
    private ComboBox<String> CustomerCombo;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField txtID;
    @FXML
    private Pane rowpane;

    private ContractControl contractControl;
    private List<ComboBox<String>> productNameCombos;
    private List<TextField> productIDTextFields;
    private List<TextField> costTextFields;
    private List<TextField> periodTextFields;
    private List<TextField> totalItemTextFields;

    public void initialize(URL location, ResourceBundle resources) {
        contractControl = new ContractControl();
        populateCustomerCombo();
        setNextContractID();

        productNameCombos = new ArrayList<>();
        productIDTextFields = new ArrayList<>();
        costTextFields = new ArrayList<>();
        periodTextFields = new ArrayList<>();
        totalItemTextFields = new ArrayList<>();

        initializeDynamicComponents();
    }

    private void initializeDynamicComponents() {
        List<Node> dynamicComponents = rowpane.getChildren();

        for (Node component : dynamicComponents) {
            if (component instanceof ComboBox) {
                ComboBox<String> productNameCombo = (ComboBox<String>) component;
                productNameCombos.add(productNameCombo);
                productNameCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        int index = productNameCombos.indexOf(productNameCombo);
                        String productId = contractControl.getProductIdByName(newValue.split("\\(")[0].trim());
                        productIDTextFields.get(index).setText(productId);

                        String cost = contractControl.getProductCostByName(newValue.split("\\(")[0].trim());
                        costTextFields.get(index).setText(cost);

                        calculateTotalItemCost(index);
                    }
                });
            } else if (component instanceof TextField) {
                TextField textField = (TextField) component;
                if (textField.getId().startsWith("productidtxt")) {
                    productIDTextFields.add(textField);
                } else if (textField.getId().startsWith("costtxt")) {
                    costTextFields.add(textField);
                } else if (textField.getId().startsWith("periodtxt")) {
                    periodTextFields.add(textField);
                    textField.textProperty().addListener((observable, oldValue, newValue) -> {
                        int index = periodTextFields.indexOf(textField);
                        calculateTotalItemCost(index);
                    });
                } else if (textField.getId().startsWith("totalitemtxt")) {
                    totalItemTextFields.add(textField);
                }
            }
        }
    }

    private void calculateTotalItemCost(int index) {
        String period = periodTextFields.get(index).getText();
        String cost = costTextFields.get(index).getText();

        if (!period.isEmpty() && !cost.isEmpty()) {
            double periodValue = Double.parseDouble(period);
            double costValue = Double.parseDouble(cost);
            double totalItemCost = periodValue * costValue;
            totalItemTextFields.get(index).setText(String.valueOf(totalItemCost));
        } else {
            totalItemTextFields.get(index).setText("");
        }
    }

    private void populateCustomerCombo() {
        CustomerCombo.getItems().addAll(contractControl.getAllCustomerNames());
    }

    private void setNextContractID() {
        String nextID = contractControl.getNextContractID();
        txtID.setText(nextID);
    }
}
