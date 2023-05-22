/*
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import trss.project.Control.UserControl;
import trss.project.Model.ModelUser;

/**
 *
 * @author خالد
 */
public class User_CL implements Initializable {
    public static int selectedId;
    @FXML private TextField txtUsername;
    @FXML private TextField txtFirst;
    @FXML private TextField txtLast;
    @FXML private TextField txtEmail;
    @FXML private ComboBox comboR;
  
    @FXML private TextField txtSearch;
    @FXML private Button btnAdd;
    @FXML private Button btnDelete;
    @FXML private Button btnModify;
    @FXML private Button btnSearch;
    @FXML private TableView table;
    @FXML private TableColumn colID;
    @FXML private TableColumn colUsername;
    @FXML private TableColumn colFirst;
    @FXML private TableColumn colLast;
    @FXML private TableColumn colEmail;
    @FXML private TableColumn colRole;        
    UserControl u = new UserControl();
    String[] Combobox = {"Admin","Ruglar user"};
    ObservableList<String> statusList = FXCollections.observableArrayList(Combobox);
  
    public void search(){
        table.setItems(u.searchProduct(txtSearch.getText()));       
    }
    
    public void add(Event e){
        ModelUser user = new ModelUser();
        user.setUserName(txtUsername.getText());
        user.setFirstName(txtFirst.getText());
        user.setLastName(txtLast.getText());
        user.setEmail(txtEmail.getText());
        user.setRole(comboR.getValue().toString());
        
        u.insert(user);
        table.setItems(u.getAllUser());
        
        txtUsername.setText("");
        txtFirst.setText("");
        txtLast.setText("");
        txtEmail.setText("");
        
        
    }
    
        
    public void modify(Event e){
        ModelUser user = new ModelUser();
        user.setUserName(txtUsername.getText());
        user.setFirstName(txtFirst.getText());
        user.setLastName(txtLast.getText());
        user.setEmail(txtEmail.getText());
        user.setRole(comboR.getValue().toString());
        
        u.modify(user);
        table.setItems(u.getAllUser());
        
        txtUsername.setText("");
        txtFirst.setText("");
        txtLast.setText("");
        txtEmail.setText("");
        
    }
        
    public void delete(Event e) throws SQLException{
            u.delete(selectedId);
            table.setItems(u.getAllUser());
            txtUsername.setText("");
            txtFirst.setText("");
            txtLast.setText("");
            txtEmail.setText("");
        }
    
    public void clickTableRow(Event e){
        ModelUser user = new ModelUser();
        user = (ModelUser) table.getSelectionModel().getSelectedItem();
        selectedId = user.getId();
        txtUsername.setText(user.getUserName());
        txtFirst.setText(user.getFirstName());
        txtLast.setText(user.getLastName());
        txtEmail.setText(user.getEmail());
        comboR.setValue(user.getRole());
            
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
/*
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colID.setCellValueFactory(new PropertyValueFactory("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory("username"));
        colFirst.setCellValueFactory(new PropertyValueFactory("first_name"));
        colLast.setCellValueFactory(new PropertyValueFactory("last_name"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colRole.setCellValueFactory(new PropertyValueFactory("role"));
        table.setItems(u.getAllUser());
        
        comboR.getItems().addAll(statusList);
    }*/
        @Override
public void initialize(URL location, ResourceBundle resources) {
    colID.setCellValueFactory(new PropertyValueFactory("id"));
    colUsername.setCellValueFactory(new PropertyValueFactory("userName"));
    colFirst.setCellValueFactory(new PropertyValueFactory("firstName"));
    colLast.setCellValueFactory(new PropertyValueFactory("lastName"));
    colEmail.setCellValueFactory(new PropertyValueFactory("email"));
    colRole.setCellValueFactory(new PropertyValueFactory("role"));
    table.setItems(u.getAllUser());
    
    comboR.getItems().addAll(statusList);

}

    
}
