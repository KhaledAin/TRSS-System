package trss.project.ViewControl;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import trss.project.Control.ReportControl;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Report_CL implements Initializable {
    @FXML
    private TextField monthL;
    @FXML
    private Label yearL;
    @FXML
    private Label LabelContract;
    @FXML
    private Label LabelCustomer;
    @FXML
    private Label LabelRevnue;
    @FXML
    private Button update;
    @FXML
    private Label LabelProduct;
    @FXML
    private ComboBox<String> topProductsComboBox;

    private ReportControl reportControl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reportControl = new ReportControl();
    }

    
    @FXML
private void handleUpdateButton(ActionEvent event) {
    // Get the selected month
    String month = monthL.getText();

    // Get the number of contracts for the specified month
    int contractCount = reportControl.getContractCountForMonth(month);

    // Update the contract label
    LabelContract.setText(String.valueOf(contractCount));

    // Get the number of customers for the specified month
    int customerCount = reportControl.getCustomerCountForMonth(month);

    // Update the customer label
    LabelCustomer.setText(String.valueOf(customerCount));

    // Get the total revenue for the specified month
    double totalRevenue = reportControl.getTotalRevenueForMonth(month);

    // Update the revenue label
    LabelRevnue.setText(String.valueOf(totalRevenue));

    // Get the total product count for the specified month
    int productCount = reportControl.getProductCountForMonth(month);

    // Update the product label
    LabelProduct.setText(String.valueOf(productCount));
    
    // Get the top 3 rented products for the specified month
    List<String> topProducts = reportControl.getTopRentedProductsForMonth(month);

    // Update the UI with the top products
    updateTopProductsUI(topProducts);
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





private void updateTopProductsUI(List<String> topProducts) {
    // Clear previous top products from UI
    topProductsComboBox.getItems().clear();

    // Update the UI with the top products
    topProductsComboBox.getItems().addAll(topProducts);
}
}
