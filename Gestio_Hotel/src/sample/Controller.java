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

public class Controller {

    @FXML
    private TextField user, password;

    /**
     * Initializes the controller class.
     */

    private void openPortal() throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("portal.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    private void failLogin() throws IOException{
        Stage stage = new Stage();
        Label label = new Label("No te has podido Loguear.\nComprueba tu usuario o contraseña");
        Scene scene = new Scene(label, 300, 100);
        stage.setTitle("Error");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void login(ActionEvent event){
        // TODO: Login check
        try
        {
            int comprobar = 0;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");
            // Preparamos la consulta
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select * from Usuaris");
            while (rs.next())
            {
                if(rs.getString(1).equals(user.getText()) && rs.getString(2).equals(password.getText())){
                    comprobar = 1;
                }else{
                    comprobar = 0;
                }
            }

            if(comprobar == 1){
                openPortal();
            }else {
                failLogin();
            }
            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void openRegister(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

}
