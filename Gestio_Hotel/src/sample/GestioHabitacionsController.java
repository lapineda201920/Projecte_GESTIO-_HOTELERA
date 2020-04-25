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

public class GestioHabitacionsController implements Initializable {

    // HABITACIONS
    @FXML
    private TableView<ModelTable> tableID;
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
    private ChoiceBox estat, tipo;

    // CREAR HABITACIONS
    @FXML
    private TextField cNumero, cPreu;
    @FXML
    private ChoiceBox cPis, cEstat, cTipo;

    @FXML
    public void initialize(URL location, ResourceBundle resources){

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select Número, Planta, Preu, Estat, Tipus from Habitacions");

            while (rs.next()) {

                oblist.add(new ModelTable(rs.getInt("Número"), rs.getInt("Planta"), rs.getInt("Preu"), rs.getString("Estat"), rs.getString("Tipus")));
            }

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        tNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tPis.setCellValueFactory(new PropertyValueFactory<>("pis"));
        tPreu.setCellValueFactory(new PropertyValueFactory<>("preu"));
        tEstada.setCellValueFactory(new PropertyValueFactory<>("estat"));
        tTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        tableID.setItems(oblist);
    }

    @FXML
    private void filtrarHabitacions() throws IOException {
        System.out.println("Filtrar | Mín:" + min.getText() + " Màx: " + max.getText() + " Estat: " + estat.getValue() + " Tipo: " + tipo.getValue());

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

        if (estat.getValue() != null){
            System.out.println("S'ha filtrat per estat!");
            if (where != " where"){
                where = where + " AND";
            }
            where = where + (" Estat = '" + estat.getValue() + "'");
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
            ResultSet rs = s.executeQuery ("select Número, Planta, Preu, Estat, Tipus from Habitacions" + where);

            while (rs.next()) {

                oblist.add(new ModelTable(rs.getInt("Número"), rs.getInt("Planta"), rs.getInt("Preu"), rs.getString("Estat"), rs.getString("Tipus")));
            }

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

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
        estat.getSelectionModel().clearSelection();
        tipo.getSelectionModel().clearSelection();
    }

    @FXML
    private void afegirHabitacio(ActionEvent event) throws IOException {

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            s.executeUpdate ("INSERT INTO `Habitacions` (`id_hab`, `Número`, `Planta`, `Preu`, `Estat`, `Tipus`) VALUES (NULL, '" + cNumero.getText() + "', '" + cPis.getValue() + "', '" + cPreu.getText() + "', '" + cEstat.getValue() + "', '" + cTipo.getValue() + "') ");

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        // RECARREGUEM LA PÀGINA PERQUÈ SE'NS MOSTRIN ELS CANVIS
        Parent blah = FXMLLoader.load(getClass().getResource("gestioHabitacions.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Gestió Habitacions");
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML // BORREM EL CONTINGUT DE LES CASELLES I AFEGIM EL CONTINGUT DE L'HABITACIÓ SEL·LECCIONADA
    private void afegirEditarHabitacio() throws IOException {

        /*netejarHabitacio();

        cNumero.setText();
        cPreu.setText();
        cPis.setValue();
        cEstat.setValue();
        cTipo.setValue();*/
    }

    @FXML
    private void editarHabitacio(ActionEvent event) throws IOException {

        /*try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            s.executeUpdate ("UPDATE `Habitacions` SET `Estat` = 'Disponible' WHERE `Habitacions`.`id_hab` = 3\n");

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        // RECARREGUEM LA PÀGINA PERQUÈ SE'NS MOSTRIN ELS CANVIS
        Parent blah = FXMLLoader.load(getClass().getResource("gestioHabitacions.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Gestió Habitacions");
        appStage.setScene(scene);
        appStage.show();*/
    }

    @FXML // BORRA EL CONTINGUT DE LES CASELLES
    private void netejarHabitacio() throws IOException {

        cNumero.clear();
        cPreu.clear();
        cPis.getSelectionModel().clearSelection();
        cEstat.getSelectionModel().clearSelection();
        cTipo.getSelectionModel().clearSelection();
    }


}
