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

public class SeleccionarHabController implements Initializable {

    // HABITACIONS
    @FXML
    private TableView<ModelTable> tableID;
    @FXML
    private TableColumn<ModelTable, Integer> tID;
    @FXML
    private TableColumn<ModelTable, Integer> tNumero;
    @FXML
    private TableColumn<ModelTable, Integer> tPis;
    @FXML
    private TableColumn<ModelTable, Integer> tPreu;
    @FXML
    private TableColumn<ModelTable, String> tEstada;
    @FXML
    private TableColumn<ModelTable, String> tTipo;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    // FILTRAR HABITACIONS
    @FXML
    private TextField min, max;
    @FXML
    private ChoiceBox tipo;

    // SELECCIONAR HABITACIONS
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
            ResultSet rs = s.executeQuery ("select * from Habitacions");

            while (rs.next()) {

                oblist.add(new ModelTable(rs.getInt("id_hab"), rs.getInt("Número"), rs.getInt("Planta"), rs.getInt("Preu"), rs.getString("Estat"), rs.getString("Tipus")));
            }

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        tID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tPis.setCellValueFactory(new PropertyValueFactory<>("pis"));
        tPreu.setCellValueFactory(new PropertyValueFactory<>("preu"));
        tEstada.setCellValueFactory(new PropertyValueFactory<>("estat"));
        tTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        tableID.setItems(oblist);
    }

    @FXML
    private void filtrarHabitacions() throws IOException {
        System.out.println("Filtrar | Mín:" + min.getText() + " Màx: " + max.getText() + " Tipo: " + tipo.getValue());

        // ESBORREM TOT EL CONTINGUT PREVI DE LA TAULA
        tableID.getItems().clear();

        // CREEM UN WHERE, EL QUAL DEPENENT DE LO QUE FILTREM, ANIRÀ AFEGINT + O - FILTRES
        String where = " where";

        if (!min.getText().isEmpty()){
            System.out.println("S'ha filtrat per min!");
            where = where + (" Preu > " + min.getText());
        }

        if (!max.getText().isEmpty()){
            System.out.println("S'ha filtrat per max!");
            if (where != " where"){
                where = where + " AND";
            }
            where = where + (" Preu < " + max.getText());
        }

        if (tipo.getValue() != null){
            System.out.println("S'ha filtrat per tipo!");
            if (where != " where"){
                where = where + " AND";
            }
            where = where + (" Tipus = '" + tipo.getValue() + "'");
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
            ResultSet rs = s.executeQuery ("select * from Habitacions" + where);

            while (rs.next()) {

                oblist.add(new ModelTable(rs.getInt("id_hab"), rs.getInt("Número"), rs.getInt("Planta"), rs.getInt("Preu"), rs.getString("Estat"), rs.getString("Tipus")));
            }

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        tID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tPis.setCellValueFactory(new PropertyValueFactory<>("pis"));
        tPreu.setCellValueFactory(new PropertyValueFactory<>("preu"));
        tEstada.setCellValueFactory(new PropertyValueFactory<>("estat"));
        tTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        tableID.setItems(oblist);
    }


    @FXML
    private void netajarFiltre() throws IOException {

        min.clear();
        max.clear();
        tipo.getSelectionModel().clearSelection();
    }

    @FXML
    private void returnID() throws IOException {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

        System.out.println("Número ID Habitació: " + tNumeroID.getText());
    }
}
