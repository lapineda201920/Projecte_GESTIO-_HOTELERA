package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class Escenes {

    protected void openWait(javafx.event.ActionEvent event,String direccion,String title) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(direccion));
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.showAndWait();
    }

    protected void open(javafx.event.ActionEvent event,String direccion,String title) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource(direccion));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Gestio Clients");
        appStage.setScene(scene);
        appStage.show();
    }

    protected void openError(String error){
        Stage stage = new Stage();
        Label label = new Label(error);
        Scene scene = new Scene(label, 300, 100);
        stage.setTitle("Error");
        stage.setScene(scene);
        stage.show();
    }

    protected void openCorrect(String text){
        Stage stage = new Stage();
        Label label = new Label(text);
        Scene scene = new Scene(label, 220, 100);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }
}
