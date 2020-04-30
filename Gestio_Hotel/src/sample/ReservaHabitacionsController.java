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

        Reserves reserva = new Reserves(tableIDReserva,tableIDR,tableIDH,tableClient,tableTipo,tableEstat,tableCost,tableDE,tableHE,tableDS,tableHS,tNumeroHabitacio,tClient,tCost,tHoraIngres,tHoraSortida,cTipoReserva,cEstatReserva,dDataIngres,dDataSortida,oblist);

        reserva.cargaTaula();
    }

    @FXML
    private void filtrarReserves() throws IOException {

        Reserves reserva = new Reserves(tableIDReserva,tableIDR,tableIDH,tableClient,tableTipo,tableEstat,tableCost,tableDE,tableHE,tableDS,tableHS,tNumeroHabitacio,tClient,tCost,tHoraIngres,tHoraSortida,cTipoReserva,cEstatReserva,dDataIngres,dDataSortida,oblist);
        reserva.filtres(tNomClient);

    }

    @FXML
    private void netajarFiltre() throws IOException {
        tNomClient.clear();
    }

    @FXML
    private void seleccionarHab(ActionEvent event) throws IOException {

        Escenes escena = new Escenes();
        escena.openWait(event,"seleccionarHab.fxml","Seleccionar Habitació");

        // LLEGIM LA CONSOLA

        String resultat = "1";

        tNumeroHabitacio.setText(resultat);
    }

    @FXML
    private void seleccionarClient(ActionEvent event) throws IOException {

        Escenes escena = new Escenes();
        escena.openWait(event,"seleccionarClient.fxml","Seleccionar Client");

        // LLEGIM LA CONSOLA

        String resultat = "1";

        tClient.setText(resultat);
    }

    @FXML
    private void netejarCreacioReserva() throws IOException {

        tNumeroHabitacio.clear();
        tClient.clear();
        tCost.clear();
        tHoraIngres.clear();
        tHoraSortida.clear();
        cTipoReserva.getSelectionModel().clearSelection();
        cEstatReserva.getSelectionModel().clearSelection();
        dDataIngres.getEditor().clear();
        dDataSortida.getEditor().clear();
    }

    @FXML
    private void afegirReserva(ActionEvent event) throws IOException {

        Reserves reserva = new Reserves(tableIDReserva,tableIDR,tableIDH,tableClient,tableTipo,tableEstat,tableCost,tableDE,tableHE,tableDS,tableHS,tNumeroHabitacio,tClient,tCost,tHoraIngres,tHoraSortida,cTipoReserva,cEstatReserva,dDataIngres,dDataSortida,oblist);

        reserva.tryReserva();

        // RECARREGUEM LA PÀGINA PERQUÈ SE'NS MOSTRIN ELS CANVIS

        Escenes escena = new Escenes();

        escena.open(event,"reservaHabitacions.fxml","Reserva Habitacions");

    }
}
