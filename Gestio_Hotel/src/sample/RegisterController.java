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

public class RegisterController {
    @FXML
    private void tornarSample(ActionEvent event) throws IOException{
        Parent blah = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Login");
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private TextField usuari,contr,nom,cognoms,dni,nacionalitat,tlf,email;

    @FXML
    private void register(ActionEvent event) {
        Register reg = new Register(usuari.getText(),contr.getText(),nom.getText(),cognoms.getText(),dni.getText(),nacionalitat.getText(),tlf.getText(),email.getText(),"","");
        reg.tryRegister();

    }
}
