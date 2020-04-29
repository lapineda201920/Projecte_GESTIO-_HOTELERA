package sample;

public class ModelTableSeleccionarClients {

    String nom,cognom,dni;
    int id;

    public ModelTableSeleccionarClients(int id, String nom, String cognom, String dni) {

        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
