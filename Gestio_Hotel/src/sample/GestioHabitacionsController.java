package sample;

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
    private TableColumn<ModelTable, Integer> tEstada;
    @FXML
    private TableColumn<ModelTable, String> tTipo;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    // FILTRAR HABITACIONS
    @FXML
    private TextField min, max;
    @FXML
    private ChoiceBox estat, tipo;

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

                oblist.add(new ModelTable(rs.getInt("Número"), rs.getInt("Planta"), rs.getInt("Preu"), rs.getInt("Estat"), rs.getString("Tipus")));
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
    }


}
