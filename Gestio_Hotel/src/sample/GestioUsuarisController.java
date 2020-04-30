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

        Usuaris usr = new Usuaris(tableIDUsuaris,tUsuari,tNom,tCognoms,tDNI,tEmail,oblist);

        usr.cargarTabla();

    }


    @FXML
    private void validar(ActionEvent event) throws IOException {

        Usuaris usr = new Usuaris(tableIDUsuaris,tUsuari,tNom,tCognoms,tDNI,tEmail,oblist);

        usr.consultarUsuari(cResposta,"acceptar");

        // RECARREGUEM LA PÀGINA PERQUÈ SE'NS MOSTRIN ELS CANVIS

        Escenes escena = new Escenes();
        escena.open(event,"gestioUsuaris.fxml","Gestio Usuaris");
    }


    @FXML
    private void denegar(ActionEvent event) throws IOException {

        Usuaris usr = new Usuaris(tableIDUsuaris,tUsuari,tNom,tCognoms,tDNI,tEmail,oblist);

        usr.consultarUsuari(cResposta,"denegar");

        // RECARREGUEM LA PÀGINA PERQUÈ SE'NS MOSTRIN ELS CANVIS

        Escenes escena = new Escenes();
        escena.open(event,"gestioUsuaris.fxml","Gestió Usuaris");
    }
}
