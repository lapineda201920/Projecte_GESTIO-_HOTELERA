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

    private void failRegister() throws IOException{
        Stage stage = new Stage();
        Label label = new Label("No te has podido registrar");
        Scene scene = new Scene(label, 220, 100);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField usuari,contr,nom,cognoms,dni,nacionalitat,tlf,email;

    @FXML
    private void register(ActionEvent event){

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");
            // Preparamos la consult
            
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select Usuari from Usuaris");
            int comprobant = 0;
            while (rs.next()){
                if(rs.getString(1).equals(usuari.getText())) {
                    comprobant = 1;
                }
            }

            if(comprobant == 0){
                String query = " insert into `Usuaris`(`Usuari`, `Password`, `Nom`, `Cognoms`, `DNI/Passaport`, `Nacionalitat`, `Telèfon`, `e-mail`)"
                        + " values (?,?,?,?, ?, ?, ?, ?)";
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conexion.prepareStatement(query);
                preparedStmt.setString (1, usuari.getText());
                preparedStmt.setString (2, contr.getText());
                preparedStmt.setString (3, nom.getText());
                preparedStmt.setString (4, cognoms.getText());
                preparedStmt.setString (5, dni.getText());
                preparedStmt.setString (6, nacionalitat.getText());
                preparedStmt.setString (7, tlf.getText());
                preparedStmt.setString (8, email.getText());

                preparedStmt.execute();
                System.out.println("registrado!");
            }else{
                failRegister();
            }
            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
