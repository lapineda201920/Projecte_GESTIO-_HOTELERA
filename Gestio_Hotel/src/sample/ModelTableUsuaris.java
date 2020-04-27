package sample;

public class ModelTableUsuaris {

    String usuari, nom, cognoms, dni, email;



    public ModelTableUsuaris (String usuari, String nom, String cognoms, String dni, String email){

        this.usuari = usuari;
        this.nom = nom;
        this.cognoms = cognoms;
        this.dni = dni;
        this.email = email;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
