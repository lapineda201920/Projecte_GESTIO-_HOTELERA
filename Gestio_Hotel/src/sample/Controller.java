package sample;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller{

    @FXML
    private TextField user, password;

    /**
     * Initializes the controller class.
     */



    @FXML
    private void login(ActionEvent event){
        Login usr = new Login(user.getText(),password.getText());
        usr.tryLogin(event);
    }

    @FXML
    private void openRegister(ActionEvent event) throws IOException {
        Escenes escena = new Escenes();
        escena.open(event,"register.fxml","Registre d'usuaris");
    }

}
