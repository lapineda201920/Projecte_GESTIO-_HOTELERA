package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class Reserves {
    private TableView<ModelTableReservaHabitacions> tableIDReserva;
    private TableColumn<ModelTableReservaHabitacions, Integer> tableIDR;
    private TableColumn<ModelTableReservaHabitacions, Integer> tableIDH;
    private TableColumn<ModelTableReservaHabitacions, String> tableClient;
    private TableColumn<ModelTableReservaHabitacions, String> tableTipo;
    private TableColumn<ModelTableReservaHabitacions, String> tableEstat;
    private TableColumn<ModelTableReservaHabitacions, Integer> tableCost;
    private TableColumn<ModelTableReservaHabitacions, Date> tableDE;
    private TableColumn<ModelTableReservaHabitacions, String> tableHE;
    private TableColumn<ModelTableReservaHabitacions, Date> tableDS;
    private TableColumn<ModelTableReservaHabitacions, String> tableHS;
    private TextField tNumeroHabitacio, tClient, tCost, tHoraIngres, tHoraSortida;
    private ChoiceBox cTipoReserva, cEstatReserva;
    private DatePicker dDataIngres, dDataSortida;

    ObservableList<ModelTableReservaHabitacions> oblist = FXCollections.observableArrayList();

    

    public Reserves(TableView<ModelTableReservaHabitacions> tableIDReserva, TableColumn<ModelTableReservaHabitacions,Integer> tableIDR, TableColumn<ModelTableReservaHabitacions,Integer> tableIDH, TableColumn<ModelTableReservaHabitacions,String> tableClient, TableColumn<ModelTableReservaHabitacions,String> tableTipo, TableColumn<ModelTableReservaHabitacions,String> tableEstat, TableColumn<ModelTableReservaHabitacions,Integer> tableCost, TableColumn<ModelTableReservaHabitacions,Date> tableDE, TableColumn<ModelTableReservaHabitacions,String> tableHE, TableColumn<ModelTableReservaHabitacions,Date> tableDS, TableColumn<ModelTableReservaHabitacions,String> tableHS, TextField tNumeroHabitacio, TextField tClient, TextField tCost, TextField tHoraIngres, TextField tHoraSortida, ChoiceBox cTipoReserva, ChoiceBox cEstatReserva, DatePicker dDataIngres, DatePicker dDataSortida, ObservableList<ModelTableReservaHabitacions> oblist) {
        this.tableIDReserva = tableIDReserva;
        this.tableIDR = tableIDR;
        this.tableIDH = tableIDH;
        this.tableClient = tableClient;
        this.tableTipo = tableTipo;
        this.tableEstat = tableEstat;
        this.tableCost = tableCost;
        this.tableDE = tableDE;
        this.tableHE = tableHE;
        this.tableDS = tableDS;
        this.tableHS = tableHS;
        this.tNumeroHabitacio = tNumeroHabitacio;
        this.tClient = tClient;
        this.tCost = tCost;
        this.tHoraIngres = tHoraIngres;
        this.tHoraSortida = tHoraSortida;
        this.cTipoReserva = cTipoReserva;
        this.cEstatReserva = cEstatReserva;
        this.dDataIngres = dDataIngres;
        this.dDataSortida = dDataSortida;
        this.oblist = oblist;
    }


    public TableView<ModelTableReservaHabitacions> getTableIDReserva() {
        return this.tableIDReserva;
    }

    public void setTableIDReserva(TableView<ModelTableReservaHabitacions> tableIDReserva) {
        this.tableIDReserva = tableIDReserva;
    }

    public TableColumn<ModelTableReservaHabitacions,Integer> getTableIDR() {
        return this.tableIDR;
    }

    public void setTableIDR(TableColumn<ModelTableReservaHabitacions,Integer> tableIDR) {
        this.tableIDR = tableIDR;
    }

    public TableColumn<ModelTableReservaHabitacions,Integer> getTableIDH() {
        return this.tableIDH;
    }

    public void setTableIDH(TableColumn<ModelTableReservaHabitacions,Integer> tableIDH) {
        this.tableIDH = tableIDH;
    }

    public TableColumn<ModelTableReservaHabitacions,String> getTableClient() {
        return this.tableClient;
    }

    public void setTableClient(TableColumn<ModelTableReservaHabitacions,String> tableClient) {
        this.tableClient = tableClient;
    }

    public TableColumn<ModelTableReservaHabitacions,String> getTableTipo() {
        return this.tableTipo;
    }

    public void setTableTipo(TableColumn<ModelTableReservaHabitacions,String> tableTipo) {
        this.tableTipo = tableTipo;
    }

    public TableColumn<ModelTableReservaHabitacions,String> getTableEstat() {
        return this.tableEstat;
    }

    public void setTableEstat(TableColumn<ModelTableReservaHabitacions,String> tableEstat) {
        this.tableEstat = tableEstat;
    }

    public TableColumn<ModelTableReservaHabitacions,Integer> getTableCost() {
        return this.tableCost;
    }

    public void setTableCost(TableColumn<ModelTableReservaHabitacions,Integer> tableCost) {
        this.tableCost = tableCost;
    }

    public TableColumn<ModelTableReservaHabitacions,Date> getTableDE() {
        return this.tableDE;
    }

    public void setTableDE(TableColumn<ModelTableReservaHabitacions,Date> tableDE) {
        this.tableDE = tableDE;
    }

    public TableColumn<ModelTableReservaHabitacions,String> getTableHE() {
        return this.tableHE;
    }

    public void setTableHE(TableColumn<ModelTableReservaHabitacions,String> tableHE) {
        this.tableHE = tableHE;
    }

    public TableColumn<ModelTableReservaHabitacions,Date> getTableDS() {
        return this.tableDS;
    }

    public void setTableDS(TableColumn<ModelTableReservaHabitacions,Date> tableDS) {
        this.tableDS = tableDS;
    }

    public TableColumn<ModelTableReservaHabitacions,String> getTableHS() {
        return this.tableHS;
    }

    public void setTableHS(TableColumn<ModelTableReservaHabitacions,String> tableHS) {
        this.tableHS = tableHS;
    }

    public TextField getTNumeroHabitacio() {
        return this.tNumeroHabitacio;
    }

    public void setTNumeroHabitacio(TextField tNumeroHabitacio) {
        this.tNumeroHabitacio = tNumeroHabitacio;
    }

    public TextField getTClient() {
        return this.tClient;
    }

    public void setTClient(TextField tClient) {
        this.tClient = tClient;
    }

    public TextField getTCost() {
        return this.tCost;
    }

    public void setTCost(TextField tCost) {
        this.tCost = tCost;
    }

    public TextField getTHoraIngres() {
        return this.tHoraIngres;
    }

    public void setTHoraIngres(TextField tHoraIngres) {
        this.tHoraIngres = tHoraIngres;
    }

    public TextField getTHoraSortida() {
        return this.tHoraSortida;
    }

    public void setTHoraSortida(TextField tHoraSortida) {
        this.tHoraSortida = tHoraSortida;
    }

    public ChoiceBox getCTipoReserva() {
        return this.cTipoReserva;
    }

    public void setCTipoReserva(ChoiceBox cTipoReserva) {
        this.cTipoReserva = cTipoReserva;
    }

    public ChoiceBox getCEstatReserva() {
        return this.cEstatReserva;
    }

    public void setCEstatReserva(ChoiceBox cEstatReserva) {
        this.cEstatReserva = cEstatReserva;
    }

    public DatePicker getDDataIngres() {
        return this.dDataIngres;
    }

    public void setDDataIngres(DatePicker dDataIngres) {
        this.dDataIngres = dDataIngres;
    }

    public DatePicker getDDataSortida() {
        return this.dDataSortida;
    }

    public void setDDataSortida(DatePicker dDataSortida) {
        this.dDataSortida = dDataSortida;
    }

    public ObservableList<ModelTableReservaHabitacions> getOblist() {
        return this.oblist;
    }

    public void setOblist(ObservableList<ModelTableReservaHabitacions> oblist) {
        this.oblist = oblist;
    }

    protected void tryReserva(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            s.executeUpdate ("INSERT INTO `Reservas` (`id_reserva`, `Tipo`, `Cost`, `Estat reserva`, `data_entrada`, `hora_entrada`, `data_sortida`, `hora_sortida`, `fk_client`, `fk_hab`) VALUES (NULL, '" + this.cTipoReserva.getValue() + "', '" + this.tCost.getText() + "', '" + this.cEstatReserva.getValue() + "', '" + this.dDataIngres.getValue() + "', '" + this.tHoraIngres.getText() + "', '" + this.dDataSortida.getValue() + "', '" + this.tHoraSortida.getText() + "', '" + this.tClient.getText() + "', '" + this.tNumeroHabitacio.getText() + "') ");

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void filtres(TextField tNomClient){
        System.out.println("Filtrar | Nom Client:" + tNomClient.getText());

        // ESBORREM TOT EL CONTINGUT PREVI DE LA TAULA
        this.tableIDReserva.getItems().clear();

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

                this.oblist.add(new ModelTableReservaHabitacions(rs.getInt("id_reserva"), rs.getInt("fk_hab"), rs.getString("Nombre"), rs.getString("Tipo"), rs.getString("Estat reserva"), rs.getInt("Cost"), rs.getDate("data_entrada"), rs.getString("hora_entrada"), rs.getDate("data_sortida"), rs.getString("hora_sortida")));
            }

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }


        this.tableIDR.setCellValueFactory(new PropertyValueFactory<>("id_reserva"));
        this.tableIDH.setCellValueFactory(new PropertyValueFactory<>("id_habitacio"));
        this.tableCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        this.tableClient.setCellValueFactory(new PropertyValueFactory<>("client"));
        this.tableTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.tableEstat.setCellValueFactory(new PropertyValueFactory<>("estat"));
        this.tableDE.setCellValueFactory(new PropertyValueFactory<>("d_entrada"));
        this.tableHE.setCellValueFactory(new PropertyValueFactory<>("hora_entrada"));
        this.tableDS.setCellValueFactory(new PropertyValueFactory<>("d_sortida"));
        this.tableHS.setCellValueFactory(new PropertyValueFactory<>("hora_sortida"));

        this.tableIDReserva.setItems(this.oblist);
    }

    protected void cargaTaula(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");

            // PREPAREM LA CONSULTA
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select * from Reservas r INNER JOIN Clientes c ON r.fk_client=c.id_client");

            while (rs.next()) {

                this.oblist.add(new ModelTableReservaHabitacions(rs.getInt("id_reserva"), rs.getInt("fk_hab"), rs.getString("Nombre"), rs.getString("Tipo"), rs.getString("Estat reserva"), rs.getInt("Cost"), rs.getDate("data_entrada"), rs.getString("hora_entrada"), rs.getDate("data_sortida"), rs.getString("hora_sortida")));
            }

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }


        this.tableIDR.setCellValueFactory(new PropertyValueFactory<>("id_reserva"));
        this.tableIDH.setCellValueFactory(new PropertyValueFactory<>("id_habitacio"));
        this.tableCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        this.tableClient.setCellValueFactory(new PropertyValueFactory<>("client"));
        this.tableTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.tableEstat.setCellValueFactory(new PropertyValueFactory<>("estat"));
        this.tableDE.setCellValueFactory(new PropertyValueFactory<>("d_entrada"));
        this.tableHE.setCellValueFactory(new PropertyValueFactory<>("hora_entrada"));
        this.tableDS.setCellValueFactory(new PropertyValueFactory<>("d_sortida"));
        this.tableHS.setCellValueFactory(new PropertyValueFactory<>("hora_sortida"));

        this.tableIDReserva.setItems(this.oblist);
    }


}
