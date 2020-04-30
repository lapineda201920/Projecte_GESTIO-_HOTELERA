package sample;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Register {

    private String usuari,contr,nom,cognoms,dni,nacionalitat,tlf,email,estat,ocupacio;

    Register(String usuari, String contr, String nom, String cognoms, String dni, String nacionalitat, String tlf, String email,String estat,String ocupacio) {
        this.usuari = usuari;
        this.contr = contr;
        this.nom = nom;
        this.cognoms = cognoms;
        this.dni = dni;
        this.nacionalitat = nacionalitat;
        this.tlf = tlf;
        this.email = email;
        this.estat = "";
        this.ocupacio = "";
    }

    public Register(String nom, String cognoms, String dni, String nacionalitat, String tlf, String email, String ocupacio, String estat) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.dni = dni;
        this.nacionalitat = nacionalitat;
        this.tlf = tlf;
        this.email = email;
        this.ocupacio = ocupacio;
        this.estat = estat;
    }

    public String getOcupacio() {
        return this.ocupacio;
    }

    public void setOcupacio(String ocupacio) {
        this.ocupacio = ocupacio;
    }

    public String getEstat() {
        return this.estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getUsuari() {
        return this.usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getContr() {
        return this.contr;
    }

    public void setContr(String contr) {
        this.contr = contr;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return this.cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNacionalitat() {
        return this.nacionalitat;
    }

    public void setNacionalitat(String nacionalitat) {
        this.nacionalitat = nacionalitat;
    }

    public String getTlf() {
        return this.tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void correctRegister() throws IOException {
        Escenes escena = new Escenes();
        escena.openCorrect("Registre realitzat correctament.");
    }

    protected void tryRegisterClient(){
        Escenes escena = new Escenes();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");
            // Preparamos la consult


            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select * from Clientes");
            int comprobant = 0;
            while (rs.next()){
                if(rs.getString(4).equals(this.dni)) {
                    comprobant = 1;
                }
            }

            if(comprobant == 0){
                String query = " insert into `Clientes`(`Nombre`, `Cognoms`, `DNI/Passaport`, `Nacionalitat`, `telèfon`, `e-mail`, `ocupació`, `estat civil`)"
                        + " values (?,?,?,?,?,?,?,?)";
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conexion.prepareStatement(query);
                preparedStmt.setString (1, this.nom);
                preparedStmt.setString (2, this.cognoms);
                preparedStmt.setString (3, this.dni);
                preparedStmt.setString (4, this.nacionalitat);
                preparedStmt.setString (5, this.tlf);
                preparedStmt.setString (6, this.email);
                preparedStmt.setString (7, this.ocupacio);
                preparedStmt.setString (8, this.estat);

                System.out.println(preparedStmt);
                System.out.println(this.dni);

                preparedStmt.execute();
                this.correctRegister();
            }else{
                escena.openError("Client ja registrat");
            }
            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void tryRegister(){

        Escenes escena = new Escenes();

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection ("jdbc:mysql://172.17.0.1:3306/Hotel","root", "claveroot");
            // Preparamos la consult

            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select Usuari from Usuaris");
            int comprobant = 0;
            int comprobantLong = 0;
            while (rs.next()){
                if(rs.getString(1).equals(this.usuari)) {
                    comprobant = 1;
                }
            }

            System.out.println(this.contr.length());

            if(this.contr.length() < 8){
                comprobantLong = 1;
            }else{
                // NOTHING TO DO
            }

            System.out.println(comprobantLong);

            if(comprobant == 0 && comprobantLong == 0){
                String query = " insert into `Usuaris`(`Usuari`, `Password`, `Nom`, `Cognoms`, `DNI/Passaport`, `Nacionalitat`, `Telèfon`, `e-mail`,`estado`)"
                        + " values (?,?,?,?,?,?,?,?,false)";
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conexion.prepareStatement(query);
                preparedStmt.setString (1, this.usuari);
                preparedStmt.setString (2, this.contr);
                preparedStmt.setString (3, this.nom);
                preparedStmt.setString (4, this.cognoms);
                preparedStmt.setString (5, this.dni);
                preparedStmt.setString (6, this.nacionalitat);
                preparedStmt.setString (7, this.tlf);
                preparedStmt.setString (8, this.email);

                preparedStmt.execute();
                this.correctRegister();
                System.out.println("registrado!");
            }else if(comprobant == 1){
                escena.openError("Usuari ja registrat");
            }else{
                escena.openError("Contrasenya necesita 8 caracters");
            }
            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
