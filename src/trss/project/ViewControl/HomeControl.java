/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trss.project.ViewControl;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author خالد
 */
public class HomeControl {
    @FXML
    Button btnP , btnC , btnU , btnB;
    public void openProduct(Event e) throws IOException{
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/trss/project/View/Product.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
    
    
        public void openUser(Event e) throws IOException{
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/trss/project/View/User.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
        
        
        
        public void openCustomer(Event e) throws IOException{
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/trss/project/View/Customer.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
            
        public void openContract(Event e) throws IOException{
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/trss/project/View/AddContract.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }           
            
}
