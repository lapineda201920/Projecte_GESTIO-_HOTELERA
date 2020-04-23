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
    private void openGestioHab() throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("gestioHabitacions.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Gestió Habitacions");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openReservaHab() throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("reservaHabitacions.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Reserva Habitacions");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openGestioUsuaris() throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("gestioUsuaris.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Gestio Usuaris");
        stage.setScene(scene);
        stage.show();
    }
}
