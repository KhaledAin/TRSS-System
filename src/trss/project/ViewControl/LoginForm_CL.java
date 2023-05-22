
package trss.project.ViewControl;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import trss.project.Control.LoginControl;
import trss.project.Model.Admin;

/**
 *
 * @author خالد
 */
public class LoginForm_CL {
    
    @FXML
    TextField txtuser;
    @FXML
    PasswordField txtpass;
    @FXML 
    Button btnsign;
    @FXML 
    Label lblmsg;
    
    Admin ad = new Admin();
    LoginControl lg = new LoginControl();
    
    
    public void isSign(Event e) throws SQLException, IOException{
        ad.setUsername(txtuser.getText());
        ad.setPassword(txtpass.getText());
        
        if(lg.isLogin(ad)){
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/trss/project/View/Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        }
        else
            lblmsg.setText("username or password is wrong!!");
    }
    
}
