package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {

    String usr,pass;

    Login(String usr,String pass){
        this.usr = usr;
        this.pass = pass;
    }


    public String getUsr() {
        return this.usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    private void openPortal(javafx.event.ActionEvent event, String usuari, String estado) throws IOException {
        Escenes escena = new Escenes();
        if (usuari.equals("admin")){
            escena.open(event,"portalAdmin.fxml","Portal Administrador");
        }
        else{
            if (estado.equals("1")) {
                escena.open(event,"portal.fxml","Portal");
            }
            else{
                escena.openError("No estas acceptat!\nDemana al admin que t'accepti");
            }
        }
    }

    private void failLogin() throws IOException{
        Escenes escena = new Escenes();
        escena.openError("No te has podido Loguear.\nComprueba tu usuario o contraseña");
    }

    protected void tryLogin(ActionEvent event) {
        try {
            int comprobar = 0;
            String usuari = "", estado = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://172.17.0.1:3306/Hotel", "root", "claveroot");
            // Preparamos la consulta
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from Usuaris");
            while (rs.next()) {
                if (rs.getString(1).equals(this.usr) && rs.getString(2).equals(this.pass)) {
                    comprobar = 1;
                    usuari = rs.getString(1);
                    estado = rs.getString("estado");
                } else {
                    //NOTHING TO DO
                }
            }

            if (comprobar == 1) {
                this.openPortal(event, usuari, estado);
            } else {
                failLogin();
            }
            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
     }
}
