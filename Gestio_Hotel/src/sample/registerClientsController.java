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

    private void failRegister() throws IOException {
        Stage stage = new Stage();
        Label label = new Label("Cliente ya registrado");
        Scene scene = new Scene(label, 220, 100);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void tornarSample(ActionEvent event) throws IOException{
        Parent blah = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Portal");
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void register(ActionEvent event){

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");
            // Preparamos la consult


            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select * from Clientes");
            int comprobant = 0;
            while (rs.next()){
                if(rs.getString(4).equals(dni.getText())) {
                    comprobant = 1;
                }
            }

            if(comprobant == 0){
                String query = " insert into `Clientes`(`Nombre`, `Cognoms`, `DNI/Passaport`, `Nacionalitat`, `telèfon`, `e-mail`, `ocupació`, `estat civil`)"
                        + " values (?,?,?,?,?,?,?,?)";
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conexion.prepareStatement(query);
                preparedStmt.setString (1, nom.getText());
                preparedStmt.setString (2, cognoms.getText());
                preparedStmt.setString (3, dni.getText());
                preparedStmt.setString (4, nacionalitat.getText());
                preparedStmt.setString (5, telefon.getText());
                preparedStmt.setString (6, email.getText());
                preparedStmt.setString (7, ocupacio.getText());
                preparedStmt.setString (8, estat.getText());

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
