/*this class used to be the controller for the GUI of the product view.
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trss.project.ViewControl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import trss.project.Control.ProductControl;
import trss.project.Model.ModelProduct;

/**
 *
 * @author خالد
 */
public class Product_CL implements Initializable{
    @FXML private TextField txtN;
    @FXML private TextField txtC;
    @FXML private TextField txtP;
    @FXML private TextField txtSearch;
    @FXML private Button btnAdd;
    @FXML private Button btnDelete;
    @FXML private Button btnModify;
    @FXML private Button btnSearch;
    @FXML private ComboBox comboS;
    @FXML private TableView table;
    @FXML private TableColumn colID;
    @FXML private TableColumn colName;
    @FXML private TableColumn colC;
    @FXML private TableColumn colP;
    @FXML private TableColumn colS;
    public static int selectedId;
        
        
    ProductControl p = new ProductControl();
    String[] Combobox = {"Avalabie","Rented" , "Faulty"};
    ObservableList<String> statusList = FXCollections.observableArrayList(Combobox);
  
    
    public void search(){
        table.setItems(p.searchProduct(txtSearch.getText()));       
    }
    
    public void add(Event e){
        ModelProduct prodcut = new ModelProduct();
        prodcut.setName(txtN.getText());
        prodcut.setCategory(txtC.getText());
        prodcut.setPrice(Double.parseDouble(txtP.getText()));
        prodcut.setStatus(comboS.getValue().toString());
        
        p.insert(prodcut);
        table.setItems(p.getAllProduct());
        
        txtN.setText("");
        txtC.setText("");
        txtP.setText("");
        
    }
    
        public void modify(Event e){
        ModelProduct prodcut = new ModelProduct();
        prodcut.setName(txtN.getText());
        prodcut.setCategory(txtC.getText());
        prodcut.setPrice(Double.parseDouble(txtP.getText()));
        prodcut.setStatus(comboS.getValue().toString());
        
        p.modify(prodcut);
        table.setItems(p.getAllProduct());
        
        txtN.setText("");
        txtC.setText("");
        txtP.setText("");
        
    }
        
        public void delete(Event e) throws SQLException{
            p.delete(selectedId);
            table.setItems(p.getAllProduct());
            txtN.setText("");
            txtC.setText("");
            txtP.setText("");
        }
        
        
        public void clickTableRow(Event e){
            ModelProduct product = new ModelProduct();
            product = (ModelProduct) table.getSelectionModel().getSelectedItem();
            selectedId = product.getId();
            txtN.setText(product.getName());
            txtC.setText(product.getCategory());
            txtP.setText(Double.toString(product.getPrice()));
            comboS.setValue(product.isStatus());
            
        }
        
        public void backHome(Event e) throws IOException{
             Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/trss/project/View/Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colID.setCellValueFactory(new PropertyValueFactory("id"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colC.setCellValueFactory(new PropertyValueFactory("category"));
        colP.setCellValueFactory(new PropertyValueFactory("price"));
        colS.setCellValueFactory(new PropertyValueFactory("status"));
        table.setItems(p.getAllProduct());
        
        comboS.getItems().addAll(statusList);
        

  
}

       
        }
    
    

 