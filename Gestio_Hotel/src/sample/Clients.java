package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Clients {

    private TableColumn<ModelTableClients, String> tNom;
    private TableColumn<ModelTableClients, String> tCognom;
    private TableColumn<ModelTableClients, String> tDNI;
    private TableColumn<ModelTableClients, String> tNacionalitat;
    private TableColumn<ModelTableClients, Integer> tTelefon;
    private TableColumn<ModelTableClients, String> tEmail;
    private TableColumn<ModelTableClients, String> tOcupacio;
    private TableColumn<ModelTableClients, String> tEstat;
    private TableView<ModelTableClients> tablaID;
    private ObservableList<ModelTableClients> oblist = FXCollections.observableArrayList();



    public Clients(TableColumn<ModelTableClients,String> tNom, TableColumn<ModelTableClients,String> tCognom, TableColumn<ModelTableClients,String> tDNI, TableColumn<ModelTableClients,String> tNacionalitat, TableColumn<ModelTableClients,Integer> tTelefon, TableColumn<ModelTableClients,String> tEmail, TableColumn<ModelTableClients,String> tOcupacio, TableColumn<ModelTableClients,String> tEstat, TableView<ModelTableClients> tablaID, ObservableList<ModelTableClients> oblist) {
        this.tNom = tNom;
        this.tCognom = tCognom;
        this.tDNI = tDNI;
        this.tNacionalitat = tNacionalitat;
        this.tTelefon = tTelefon;
        this.tEmail = tEmail;
        this.tOcupacio = tOcupacio;
        this.tEstat = tEstat;
        this.tablaID = tablaID;
        this.oblist = oblist;
    }
    

    public TableColumn<ModelTableClients,String> getTNom() {
        return this.tNom;
    }

    public void setTNom(TableColumn<ModelTableClients,String> tNom) {
        this.tNom = tNom;
    }

    public TableColumn<ModelTableClients,String> getTCognom() {
        return this.tCognom;
    }

    public void setTCognom(TableColumn<ModelTableClients,String> tCognom) {
        this.tCognom = tCognom;
    }

    public TableColumn<ModelTableClients,String> getTDNI() {
        return this.tDNI;
    }

    public void setTDNI(TableColumn<ModelTableClients,String> tDNI) {
        this.tDNI = tDNI;
    }

    public TableColumn<ModelTableClients,String> getTNacionalitat() {
        return this.tNacionalitat;
    }

    public void setTNacionalitat(TableColumn<ModelTableClients,String> tNacionalitat) {
        this.tNacionalitat = tNacionalitat;
    }

    public TableColumn<ModelTableClients,Integer> getTTelefon() {
        return this.tTelefon;
    }

    public void setTTelefon(TableColumn<ModelTableClients,Integer> tTelefon) {
        this.tTelefon = tTelefon;
    }

    public TableColumn<ModelTableClients,String> getTEmail() {
        return this.tEmail;
    }

    public void setTEmail(TableColumn<ModelTableClients,String> tEmail) {
        this.tEmail = tEmail;
    }

    public TableColumn<ModelTableClients,String> getTOcupacio() {
        return this.tOcupacio;
    }

    public void setTOcupacio(TableColumn<ModelTableClients,String> tOcupacio) {
        this.tOcupacio = tOcupacio;
    }

    public TableColumn<ModelTableClients,String> getTEstat() {
        return this.tEstat;
    }

    public void setTEstat(TableColumn<ModelTableClients,String> tEstat) {
        this.tEstat = tEstat;
    }

    public TableView<ModelTableClients> getTablaID() {
        return this.tablaID;
    }

    public void setTablaID(TableView<ModelTableClients> tablaID) {
        this.tablaID = tablaID;
    }

    public ObservableList<ModelTableClients> getOblist() {
        return this.oblist;
    }

    public void setOblist(ObservableList<ModelTableClients> oblist) {
        this.oblist = oblist;
    }

    protected void filtres(TextField cNom,TextField cCognoms,TextField cDNI,TextField cNacionalitat,TextField cTelefon,TextField cOcupacio,TextField cEmail,TextField cEstat){
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

    protected void contingutTaula(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select Nombre,Cognoms,`DNI/Passaport`,Nacionalitat,telèfon,`e-mail`,ocupació,`estat civil` from Clientes");

            while (rs.next()) {
                this.oblist.add(new ModelTableClients(rs.getString("Nombre"),rs.getString("Cognoms"),rs.getString("DNI/Passaport"),rs.getString("Nacionalitat"),rs.getString("e-mail"),rs.getString("ocupació"),rs.getString("estat civil"),rs.getInt("telèfon")));
            }

            conexion.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        this.tNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.tCognom.setCellValueFactory(new PropertyValueFactory<>("cognom"));
        this.tDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        this.tNacionalitat.setCellValueFactory(new PropertyValueFactory<>("nacionalitat"));
        this.tTelefon.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.tEmail.setCellValueFactory(new PropertyValueFactory<>("ocupacio"));
        this.tOcupacio.setCellValueFactory(new PropertyValueFactory<>("estat"));
        this.tEstat.setCellValueFactory(new PropertyValueFactory<>("telefon"));

        this.tablaID.setItems(this.oblist);
    }


}
