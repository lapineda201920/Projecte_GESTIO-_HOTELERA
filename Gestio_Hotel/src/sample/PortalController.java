package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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

import javax.swing.*;

public class PortalController {

    @FXML
    private void openGestioHab(ActionEvent event) throws IOException{
        Escenes escena = new Escenes();
        escena.open(event,"gestioHabitacions.fxml","Gestió Habitacions");
    }

    @FXML
    private void openGestioClients(ActionEvent event) throws IOException{
        Escenes escena = new Escenes();
        escena.open(event,"gestioClients.fxml","Gestió Clients");
    }

    @FXML
    private void openReservaHab(ActionEvent event) throws IOException{
        Escenes escena = new Escenes();
        escena.open(event,"reservaHabitacions.fxml","Reserva Habitacions");
    }

    @FXML
    private void openGestioUsuaris(ActionEvent event) throws IOException{
        Escenes escena = new Escenes();
        escena.open(event,"gestioUsuaris.fxml","Gestio Usuaris");
    }
}
