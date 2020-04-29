package sample;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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


import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ReservaHabitacionsController implements Initializable {

    // RESERVA HABITACIONS
    @FXML
    private TableView<ModelTableReservaHabitacions> tableIDReserva;
    @FXML
    private TableColumn<ModelTableReservaHabitacions, Integer> tableIDR;
    @FXML
    private TableColumn<ModelTableReservaHabitacions, Integer> tableIDH;
    @FXML
    private TableColumn<ModelTableReservaHabitacions, String> tableClient;
    @FXML
    private TableColumn<ModelTableReservaHabitacions, String> tableTipo;
    @FXML
    private TableColumn<ModelTableReservaHabitacions, String> tableEstat;
    @FXML
    private TableColumn<ModelTableReservaHabitacions, Integer> tableCost;
    @FXML
    private TableColumn<ModelTableReservaHabitacions, Date> tableDE;
    @FXML
    private TableColumn<ModelTableReservaHabitacions, String> tableHE;
    @FXML
    private TableColumn<ModelTableReservaHabitacions, Date> tableDS;
    @FXML
    private TableColumn<ModelTableReservaHabitacions, String> tableHS;

    ObservableList<ModelTableReservaHabitacions> oblist = FXCollections.observableArrayList();


    // FILTRAR RESERVA HABITACIONS
    @FXML
    private TextField tNomClient;

    // CREAR RESERVES HABITACIONS
    @FXML
    private TextField tNumeroHabitacio, tClient, tCost, tHoraIngres, tHoraSortida;
    @FXML
    private ChoiceBox cTipoReserva, cEstatReserva;
    @FXML
    private DatePicker dDataIngres, dDataSortida;


    @FXML
    public void initialize(URL location, ResourceBundle resources){

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select * from Reservas r INNER JOIN Clientes c ON r.fk_client=c.id_client");

            while (rs.next()) {

                oblist.add(new ModelTableReservaHabitacions(rs.getInt("id_reserva"), rs.getInt("fk_hab"), rs.getString("Nombre"), rs.getString("Tipo"), rs.getString("Estat reserva"), rs.getInt("Cost"), rs.getDate("data_entrada"), rs.getString("hora_entrada"), rs.getDate("data_sortida"), rs.getString("hora_sortida")));
            }

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }


        tableIDR.setCellValueFactory(new PropertyValueFactory<>("id_reserva"));
        tableIDH.setCellValueFactory(new PropertyValueFactory<>("id_habitacio"));
        tableCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        tableClient.setCellValueFactory(new PropertyValueFactory<>("client"));
        tableTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tableEstat.setCellValueFactory(new PropertyValueFactory<>("estat"));
        tableDE.setCellValueFactory(new PropertyValueFactory<>("d_entrada"));
        tableHE.setCellValueFactory(new PropertyValueFactory<>("hora_entrada"));
        tableDS.setCellValueFactory(new PropertyValueFactory<>("d_sortida"));
        tableHS.setCellValueFactory(new PropertyValueFactory<>("hora_sortida"));

        tableIDReserva.setItems(oblist);
    }

    @FXML
    private void filtrarReserves() throws IOException {
        System.out.println("Filtrar | Nom Client:" + tNomClient.getText());

        // ESBORREM TOT EL CONTINGUT PREVI DE LA TAULA
        tableIDReserva.getItems().clear();

        // CREEM UN WHERE, EL QUAL DEPENENT DE LO QUE FILTREM, ANIRÀ AFEGINT + O - FILTRES
        String where = " where";

        if (!tNomClient.getText().isEmpty()){
            System.out.println("S'ha filtrat per nom client!");
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
            ResultSet rs = s.executeQuery ("select * from Reservas r INNER JOIN Clientes c ON r.fk_client=c.id_client" + where);

            while (rs.next()) {

                oblist.add(new ModelTableReservaHabitacions(rs.getInt("id_reserva"), rs.getInt("fk_hab"), rs.getString("Nombre"), rs.getString("Tipo"), rs.getString("Estat reserva"), rs.getInt("Cost"), rs.getDate("data_entrada"), rs.getString("hora_entrada"), rs.getDate("data_sortida"), rs.getString("hora_sortida")));
            }

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }


        tableIDR.setCellValueFactory(new PropertyValueFactory<>("id_reserva"));
        tableIDH.setCellValueFactory(new PropertyValueFactory<>("id_habitacio"));
        tableCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        tableClient.setCellValueFactory(new PropertyValueFactory<>("client"));
        tableTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tableEstat.setCellValueFactory(new PropertyValueFactory<>("estat"));
        tableDE.setCellValueFactory(new PropertyValueFactory<>("d_entrada"));
        tableHE.setCellValueFactory(new PropertyValueFactory<>("hora_entrada"));
        tableDS.setCellValueFactory(new PropertyValueFactory<>("d_sortida"));
        tableHS.setCellValueFactory(new PropertyValueFactory<>("hora_sortida"));

        tableIDReserva.setItems(oblist);

    }

    @FXML
    private void netajarFiltre() throws IOException {

        tNomClient.clear();
    }

    @FXML
    private void seleccionarHab() throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("seleccionarHab.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Seleccionar Habitació");
        stage.setScene(scene);
        stage.showAndWait();

        // LLEGIM LA CONSOLA

        String resultat = "1";

        tNumeroHabitacio.setText(resultat);
    }

    @FXML
    private void seleccionarClient() throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("seleccionarClient.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Seleccionar Client");
        stage.setScene(scene);
        stage.showAndWait();

        // LLEGIM LA CONSOLA

        String resultat = "1";

        tClient.setText(resultat);
    }


}
