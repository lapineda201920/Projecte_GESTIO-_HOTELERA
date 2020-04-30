package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Usuaris {
    private TableView<ModelTableUsuaris> tableIDUsuaris;
    private TableColumn<ModelTableUsuaris, String> tUsuari;
    private TableColumn<ModelTableUsuaris, String> tNom;
    private TableColumn<ModelTableUsuaris, String> tCognoms;
    private TableColumn<ModelTableUsuaris, String> tDNI;
    private TableColumn<ModelTableUsuaris, String> tEmail;

    ObservableList<ModelTableUsuaris> oblist = FXCollections.observableArrayList();


    public Usuaris(TableView<ModelTableUsuaris> tableIDUsuaris, TableColumn<ModelTableUsuaris,String> tUsuari, TableColumn<ModelTableUsuaris,String> tNom, TableColumn<ModelTableUsuaris,String> tCognoms, TableColumn<ModelTableUsuaris,String> tDNI, TableColumn<ModelTableUsuaris,String> tEmail, ObservableList<ModelTableUsuaris> oblist) {
        this.tableIDUsuaris = tableIDUsuaris;
        this.tUsuari = tUsuari;
        this.tNom = tNom;
        this.tCognoms = tCognoms;
        this.tDNI = tDNI;
        this.tEmail = tEmail;
        this.oblist = oblist;
    }


    public TableView<ModelTableUsuaris> getTableIDUsuaris() {
        return this.tableIDUsuaris;
    }

    public void setTableIDUsuaris(TableView<ModelTableUsuaris> tableIDUsuaris) {
        this.tableIDUsuaris = tableIDUsuaris;
    }

    public TableColumn<ModelTableUsuaris,String> getTUsuari() {
        return this.tUsuari;
    }

    public void setTUsuari(TableColumn<ModelTableUsuaris,String> tUsuari) {
        this.tUsuari = tUsuari;
    }

    public TableColumn<ModelTableUsuaris,String> getTNom() {
        return this.tNom;
    }

    public void setTNom(TableColumn<ModelTableUsuaris,String> tNom) {
        this.tNom = tNom;
    }

    public TableColumn<ModelTableUsuaris,String> getTCognoms() {
        return this.tCognoms;
    }

    public void setTCognoms(TableColumn<ModelTableUsuaris,String> tCognoms) {
        this.tCognoms = tCognoms;
    }

    public TableColumn<ModelTableUsuaris,String> getTDNI() {
        return this.tDNI;
    }

    public void setTDNI(TableColumn<ModelTableUsuaris,String> tDNI) {
        this.tDNI = tDNI;
    }

    public TableColumn<ModelTableUsuaris,String> getTEmail() {
        return this.tEmail;
    }

    public void setTEmail(TableColumn<ModelTableUsuaris,String> tEmail) {
        this.tEmail = tEmail;
    }

    public ObservableList<ModelTableUsuaris> getOblist() {
        return this.oblist;
    }

    public void setOblist(ObservableList<ModelTableUsuaris> oblist) {
        this.oblist = oblist;
    }

    protected void consultarUsuari(TextField cResposta,String opcio){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");
            if(opcio.equals("acceptar")){
                Statement s = conexion.createStatement();
                s.executeUpdate ("UPDATE `Usuaris` SET `estado` = '1' WHERE `Usuaris`.`Usuari` = '" + cResposta.getText() + "'");
            }else{
                Statement s = conexion.createStatement();
                s.executeUpdate ("DELETE FROM `Usuaris` WHERE `Usuaris`.`Usuari` = '"+ cResposta.getText() + "'");
            }
            // PREPAREM LA CONSULTA


            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void cargarTabla(){
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


}
