/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trss.project.ViewControl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import trss.project.Control.CustomerControl;
import trss.project.Model.ModelCustomer;

/**
 *
 * @author خالد
 */
public class Customer_CL implements Initializable {
    
    @FXML private TextField txtN;
    @FXML private TextField txtNIC;
    @FXML private TextField txtAddress;
    @FXML private TextField txtPhone;
    @FXML private TextField txtSearch;
    @FXML private Button btnAdd;
    @FXML private Button btnDelete;
    @FXML private Button btnModify;
    @FXML private Button btnSearch;
    @FXML private TableView table;
    @FXML private TableColumn colID;
    @FXML private TableColumn colName;
    @FXML private TableColumn colNIC;
    @FXML private TableColumn colAddress;
    @FXML private TableColumn colPhone;
    public static int selectedIdCustomer;
  
    
    CustomerControl  c = new CustomerControl();
    
        public void search(){
        table.setItems(c.searchCustomer(txtSearch.getText()));       
    }
        
        public void add(Event e){
        ModelCustomer customer = new ModelCustomer();
        customer.setName(txtN.getText());
        customer.setAdress(txtAddress.getText());
        customer.setNic(txtNIC.getText());
        customer.setPhone(txtPhone.getText());
        
        c.insert(customer);
        table.setItems(c.getAllCustomers());
        
        txtN.setText("");
        txtNIC.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
    }
        
        

        public void modify(Event e){
        ModelCustomer customer = new ModelCustomer();
        customer.setName(txtN.getText());
        customer.setNic(txtNIC.getText());
        customer.setAdress(txtAddress.getText());
        customer.setPhone(txtPhone.getText());
        
        c.modify(customer);
        table.setItems(c.getAllCustomers());
        
        txtN.setText("");
        txtNIC.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        
    }
        
        public void delete(Event e) throws SQLException{
            c.delete(selectedIdCustomer);
            table.setItems(c.getAllCustomers());
            txtN.setText("");
            txtNIC.setText("");
            txtAddress.setText("");
            txtPhone.setText("");
        }
        
        
        public void clickTableRow2(Event e){
            ModelCustomer customer = new ModelCustomer();
              if (table != null) {
                  customer = (ModelCustomer) table.getSelectionModel().getSelectedItem();
              }
              if (customer != null) {
                  selectedIdCustomer = customer.getId();
                  txtN.setText(customer.getName());
                  txtNIC.setText(customer.getNic());
                  txtAddress.setText((customer.getAdress()));
                  txtPhone.setText(customer.getPhone());
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
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colID.setCellValueFactory(new PropertyValueFactory("id"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory("Nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory("adress"));
        colPhone.setCellValueFactory(new PropertyValueFactory("phone"));
        table.setItems(c.getAllCustomers());
        

                
        }
    
    
    
}
