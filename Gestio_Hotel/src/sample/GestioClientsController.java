package sample;

import com.mysql.cj.xdevapi.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select Nombre,Cognoms,'DNI/Passaport',Nacionalitat,telèfon,'e-mail',ocupació,'estat civil' from Clientes");

            while (rs.next()) {

                oblist.add(new ModelTableClients(rs.getString("Nombre"),rs.getString("Cognoms"),rs.getString("DNI/Passaport"),rs.getString("Nacionalitat"),rs.getString("e-mail"),rs.getString("ocupació"),rs.getString("estat civil"),rs.getInt("telèfon")));
            }

            conexion.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        tNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tCognom.setCellValueFactory(new PropertyValueFactory<>("cognom"));
        tDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        tNacionalitat.setCellValueFactory(new PropertyValueFactory<>("nacionalitat"));
        tTelefon.setCellValueFactory(new PropertyValueFactory<>("email"));
        tEmail.setCellValueFactory(new PropertyValueFactory<>("ocupacio"));
        tOcupacio.setCellValueFactory(new PropertyValueFactory<>("estat"));
        tEstat.setCellValueFactory(new PropertyValueFactory<>("telefon"));

        tablaID.setItems(oblist);
    }

    @FXML
    private void filtresClients() throws IOException {

        // ESBORREM TOT EL CONTINGUT PREVI DE LA TAULA
        tablaID.getItems().clear();

        // CREEM UN WHERE, EL QUAL DEPENENT DE LO QUE FILTREM, ANIRÀ AFEGINT + O - FILTRES
        String where = " where";

        if (!(cNom.getText().equals(""))){
            System.out.println("S'ha filtrat per nom!");
            if (where != " where"){
                where = where + " AND";
            }
            where = where + (" Nombre = '" + cNom.getText() + "'");
        }else{
            // NOTHING TO DO
        }

        if (!(cCognoms.getText().equals(""))){
            System.out.println("S'ha filtrat per cognom!");
            if (where != " where"){
                where = where + " AND";
            }
            where = where + (" Cognom = '" + cCognoms.getText() + "'");
        }else{
            // NOTHING TO DO
        }

        if (!(cDNI.getText().equals(""))){
            System.out.println("S'ha filtrat per DNI!");
            if (where != " where"){
                where = where + " AND";
            }
            where = where + (" 'DNI/Passaport' = '" + cDNI.getText() + "'");
        }else{
            // NOTHING TO DO
        }

        if (!(cNacionalitat.getText().equals(""))){
            System.out.println("S'ha filtrat per DNI!");
            if (where != " where"){
                where = where + " AND";
            }
            where = where + (" 'Nacionalitat' = '" + cNacionalitat.getText() + "'");
        }else{
            // NOTHING TO DO
        }

        if (!(cTelefon.getText().equals(""))){
            System.out.println("S'ha filtrat per telefon!");
            if (where != " where"){
                where = where + " AND";
            }
            where = where + (" 'telèfon' = '" + cTelefon.getText() + "'");
        }else{
            // NOTHING TO DO
        }

        if (!(cOcupacio.getText().equals(""))){
            System.out.println("S'ha filtrat per telefon!");
            if (where != " where"){
                where = where + " AND";
            }
            where = where + (" 'ocupació' = '" + cOcupacio.getText() + "'");
        }else{
            // NOTHING TO DO
        }

        if (!(cEmail.getText().equals(""))){
            System.out.println("S'ha filtrat per email!");
            if (where != " where"){
                where = where + " AND";
            }
            where = where + (" 'e-mail' = '" + cEmail.getText() + "'");
        }else{
            // NOTHING TO DO
        }

        if (!(cEstat.getText().equals(""))){
            System.out.println("S'ha filtrat per estat!");
            if (where != " where"){
                where = where + " AND";
            }
            where = where + (" 'estat civil' = '" + cEstat.getText() + "'");
        }else{
            // NOTHING TO DO
        }

        // SI HEM FILTRAT RES (SOLAMENT PREMUT EL BOTÓ , PERÒ SENSE POSAR FILTRES), ELIMINEM EL ' where'
        if (where.equals(" where")){
            where = "";
        }

        System.out.println(where);

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select * from Clientes" + where);

            while (rs.next()) {
                oblist.add(new ModelTableClients(rs.getString("Nombre"),rs.getString("Cognoms"),rs.getString("DNI/Passaport"),rs.getString("Nacionalitat"),rs.getString("e-mail"),rs.getString("ocupació"),rs.getString("estat civil"),rs.getInt("telèfon")));
            }

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        tNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tCognom.setCellValueFactory(new PropertyValueFactory<>("cognom"));
        tDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        tNacionalitat.setCellValueFactory(new PropertyValueFactory<>("nacionalitat"));
        tTelefon.setCellValueFactory(new PropertyValueFactory<>("email"));
        tEmail.setCellValueFactory(new PropertyValueFactory<>("ocupacio"));
        tOcupacio.setCellValueFactory(new PropertyValueFactory<>("estat"));
        tEstat.setCellValueFactory(new PropertyValueFactory<>("telefon"));

        tablaID.setItems(oblist);
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
