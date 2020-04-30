package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static sample.ReservaHabitacionsController.setResultat;

public class SeleccionarClientController  implements Initializable {


    @FXML
    private TableView<ModelTableSeleccionarClients> tableID;
    @FXML
    private TableColumn<ModelTableSeleccionarClients, String> tID;
    @FXML
    private TableColumn<ModelTableSeleccionarClients, String> tNom;
    @FXML
    private TableColumn<ModelTableSeleccionarClients, String> tCognoms;
    @FXML
    private TableColumn<ModelTableSeleccionarClients, String> tDNI;



    ObservableList<ModelTableSeleccionarClients> oblist = FXCollections.observableArrayList();


    // FILTRAR CLIENTS
    @FXML
    private TextField tNomClient;


    // SELECCIONAR CLIENTS
    @FXML
    private TextField tNumeroID;
    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    public void initialize(URL location, ResourceBundle resources){

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select * from Clientes");

            while (rs.next()) {

                oblist.add(new ModelTableSeleccionarClients(rs.getInt("id_client"), rs.getString("Nombre"), rs.getString("Cognoms"), rs.getString("DNI/Passaport")));
            }

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        tID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tCognoms.setCellValueFactory(new PropertyValueFactory<>("cognom"));
        tDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));

        tableID.setItems(oblist);
    }

    @FXML
    private void filtrarClients() throws IOException {

        // ESBORREM TOT EL CONTINGUT PREVI DE LA TAULA
        tableID.getItems().clear();

        // CREEM UN WHERE, EL QUAL DEPENENT DE LO QUE FILTREM, ANIRÀ AFEGINT + O - FILTRES
        String where = " where";

        if (!tNomClient.getText().isEmpty()){
            System.out.println("S'ha filtrat per nom!");
            where = where + (" Nombre LIKE '" + tNomClient.getText() + "'");
        }


        // SI HEM FILTRAT RES (SOLAMENT PREMUT EL BOTÓ , PERÒ SENSE POSAR FILTRES), ELIMINEM EL ' where'
        if (where.equals(" where")){
            where = "";
        }

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select * from Clientes" + where);

            while (rs.next()) {

                oblist.add(new ModelTableSeleccionarClients(rs.getInt("id_client"), rs.getString("Nombre"), rs.getString("Cognoms"), rs.getString("DNI/Passaport")));
            }

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        tID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tCognoms.setCellValueFactory(new PropertyValueFactory<>("cognom"));
        tDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));

        tableID.setItems(oblist);
    }


    @FXML
    private void netajarFiltre() throws IOException {

        tNomClient.clear();
    }

    @FXML
    private void returnID() throws IOException {

        ReservaHabitacionsController.setResultat((String) tNumeroID.getText());

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

        System.out.println("Número ID Client: " + tNumeroID.getText());
    }
}
