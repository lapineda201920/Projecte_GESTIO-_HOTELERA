package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller {

    @FXML
    private TextField user, password;

    /**
     * Initializes the controller class.
     */

    @FXML
    private void login(ActionEvent event){
        // TODO: Login check
        System.out.println(password.getText());
        System.out.println(user.getText());

    }

    @FXML
    private void openRegister(ActionEvent event) throws IOException {
        // TODO: Open register FXML
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Programa de reserves");
        stage.setScene(scene);
        stage.show();
    }
}
