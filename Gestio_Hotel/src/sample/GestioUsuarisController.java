package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class GestioUsuarisController implements Initializable {

    // HABITACIONS
    @FXML
    private TableView<ModelTableUsuaris> tableIDUsuaris;
    @FXML
    private TableColumn<ModelTableUsuaris, String> tUsuari;
    @FXML
    private TableColumn<ModelTableUsuaris, String> tNom;
    @FXML
    private TableColumn<ModelTableUsuaris, String> tCognoms;
    @FXML
    private TableColumn<ModelTableUsuaris, String> tDNI;
    @FXML
    private TableColumn<ModelTableUsuaris, String> tEmail;

    ObservableList<ModelTableUsuaris> oblist = FXCollections.observableArrayList();

    // Nom del Usuari
    @FXML
    private TextField cResposta;

    @FXML
    public void initialize(URL location, ResourceBundle resources){

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select `Usuari`, `Nom`, `Cognoms`, `DNI/Passaport`, `e-mail` from `Usuaris` where `estado` = 0");

            while (rs.next()) {

                oblist.add(new ModelTableUsuaris(rs.getString("Usuari"), rs.getString("Nom"), rs.getString("Cognoms"), rs.getString("DNI/Passaport"), rs.getString("e-mail")));
            }

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        tUsuari.setCellValueFactory(new PropertyValueFactory<>("usuari"));
        tNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tCognoms.setCellValueFactory(new PropertyValueFactory<>("cognoms"));
        tDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        tEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableIDUsuaris.setItems(oblist);
    }


    @FXML
    private void validar(ActionEvent event) throws IOException {

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            s.executeUpdate ("UPDATE `Usuaris` SET `estado` = '1' WHERE `Usuaris`.`Usuari` = '" + cResposta.getText() + "'");

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        // RECARREGUEM LA PÀGINA PERQUÈ SE'NS MOSTRIN ELS CANVIS
        Parent blah = FXMLLoader.load(getClass().getResource("gestioUsuaris.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Gestió Usuaris");
        appStage.setScene(scene);
        appStage.show();
    }


    @FXML
    private void denegar(ActionEvent event) throws IOException {

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            s.executeUpdate ("DELETE FROM `Usuaris` WHERE `Usuaris`.`Usuari` = '"+ cResposta.getText() + "'");

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        // RECARREGUEM LA PÀGINA PERQUÈ SE'NS MOSTRIN ELS CANVIS
        Parent blah = FXMLLoader.load(getClass().getResource("gestioUsuaris.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Gestió Usuaris");
        appStage.setScene(scene);
        appStage.show();
    }
}
