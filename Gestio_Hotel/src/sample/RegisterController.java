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

    private void failRegister(String error) throws IOException{
        Stage stage = new Stage();
        Label label = new Label(error);
        Scene scene = new Scene(label, 420, 100);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    private void correctRegister() throws IOException{
        Stage stage = new Stage();
        Label label = new Label("Usuari registrat, esperi la validació del administrador");
        Scene scene = new Scene(label, 220, 100);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void tornarSample(ActionEvent event) throws IOException{
        Parent blah = FXMLLoader.load(getClass().getResource("gestioClients.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Gestio clients");
        appStage.setScene(scene);
        appStage.show();
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
            int comprobantLong = 0;
            while (rs.next()){
                if(rs.getString(1).equals(usuari.getText())) {
                    comprobant = 1;
                }
            }

            if(contr.getText().length() < 8){
                comprobantLong = 1;
            }else{
                // NOTHING TO DO
            }


            if(comprobant == 0 && comprobantLong == 0){
                String query = " insert into `Usuaris`(`Usuari`, `Password`, `Nom`, `Cognoms`, `DNI/Passaport`, `Nacionalitat`, `Telèfon`, `e-mail`,`estado`)"
                        + " values (?,?,?,?,?,?,?,?,false)";
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
                correctRegister();
                System.out.println("registrado!");
            }else if(comprobant == 1){
                failRegister("Usuari ja registrat");
            }else{
                failRegister("Contrasenya necesita 8 caracters");
            }
            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
