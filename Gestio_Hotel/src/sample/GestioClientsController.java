package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestioClientsController implements Initializable {

    /* ATTR TABLE */

    @FXML
    private TableView<ModelTableClients> tablaID;
    @FXML
    private TableColumn<ModelTableClients, String> tNom;
    @FXML
    private TableColumn<ModelTableClients, String> tCognom;
    @FXML
    private TableColumn<ModelTableClients, String> tDNI;
    @FXML
    private TableColumn<ModelTableClients, String> tNacionalitat;
    @FXML
    private TableColumn<ModelTableClients, Integer> tTelefon;
    @FXML
    private TableColumn<ModelTableClients, String> tEmail;
    @FXML
    private TableColumn<ModelTableClients, String> tOcupacio;
    @FXML
    private TableColumn<ModelTableClients, String> tEstat;

    ObservableList<ModelTableClients> oblist = FXCollections.observableArrayList();

    /* ATR FILTRES */

    @FXML
    private TextField cNom, cCognoms,cDNI,cNacionalitat,cTelefon,cOcupacio,cEmail,cEstat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Clients clients = new Clients(tNom,tCognom,tDNI,tNacionalitat,tTelefon,tEmail,tOcupacio,tEstat,tablaID,oblist);
        clients.contingutTaula();
    }

    @FXML
    private void filtresClients() throws IOException {
        Clients clients = new Clients(tNom,tCognom,tDNI,tNacionalitat,tTelefon,tEmail,tOcupacio,tEstat,tablaID,oblist);
        clients.filtres(cNom, cCognoms,cDNI,cNacionalitat,cTelefon,cOcupacio,cEmail,cEstat);
    }


    @FXML
    private void openRegister(ActionEvent event) throws IOException{
        Escenes escena = new Escenes();
        escena.open(event,"registerClients.fxml","Registre de clients");
    }

    @FXML
    private void netejarFiltres() throws IOException {
        cNom.clear();
        cCognoms.clear();
        cOcupacio.clear();
        cDNI.clear();
        cNacionalitat.clear();
        cTelefon.clear();
    }

}
