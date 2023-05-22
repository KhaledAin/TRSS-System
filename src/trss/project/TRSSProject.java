/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package trss.project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author خالد
 */
public class TRSSProject extends Application {
    
    @Override
    public void start(Stage stage)throws IOException {
     
        // Specify the absolute path to the FXML file
        String fxmlPath = "C:/Users/خالد/Documents/NetBeansProjects/TRSS Project/src/trss/project/View/LoginForm.fxml";
        URL fxmlUrl = new File(fxmlPath).toURI().toURL();
        
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

        // Set up the scene and show the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
     
    }
       
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
