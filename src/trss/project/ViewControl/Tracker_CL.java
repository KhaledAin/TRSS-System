/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trss.project.ViewControl;


import java.io.IOException;
import java.sql.Date;
import trss.project.Model.ModelContract;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import trss.project.Control.TrackerControl;

public class Tracker_CL {

    @FXML
    private TableView<ModelContract> table;

    @FXML
    private TableColumn<ModelContract, Integer> contractID;

    @FXML
    private TableColumn<ModelContract, Integer> customerID;

    @FXML
    private TableColumn<ModelContract, Double> totalCost;

    @FXML
    private TableColumn<ModelContract, Double> deposit;

    @FXML
    private TableColumn<ModelContract, Date> date;

    private TrackerControl trackerControl;

    public void initialize() {
        trackerControl = new TrackerControl();
        setupTableColumns();
        retrieveContracts();
    }

    private void setupTableColumns() {
        contractID.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        totalCost.setCellValueFactory(new PropertyValueFactory<>("total_cost"));
        deposit.setCellValueFactory(new PropertyValueFactory<>("deposit"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
}


    private void retrieveContracts() {
        List<ModelContract> contracts = trackerControl.retrieveContractsFromDatabase();
        table.getItems().addAll(contracts);
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
}
