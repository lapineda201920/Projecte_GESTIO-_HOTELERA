package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class registerClientsController {

    @FXML
    private TextField nom, cognoms, dni, nacionalitat, telefon, email, ocupacio, estat;

    @FXML
    private void tornarSample(ActionEvent event) throws IOException{
        Escenes escena = new Escenes();
        escena.open(event,"gestioClients.fxml","Gestio Clients");
    }

    @FXML
    private void register(ActionEvent event) {
        Register reg = new Register(nom.getText(), cognoms.getText(), dni.getText(), nacionalitat.getText(), telefon.getText(), email.getText(), ocupacio.getText(), estat.getText());
        reg.tryRegisterClient();
    }
}
