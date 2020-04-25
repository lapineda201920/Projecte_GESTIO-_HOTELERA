package sample;

public class ModelTableClients {

    String nom,cognom,dni,nacionalitat,email,ocupacio,estat;
    int telefon;

    public ModelTableClients(String nom, String cognom, String dni, String nacionalitat, String email, String ocupacio, String estat, int telefon) {
        this.nom = nom;
        this.cognom = cognom;
        this.dni = dni;
        this.nacionalitat = nacionalitat;
        this.email = email;
        this.ocupacio = ocupacio;
        this.estat = estat;
        this.telefon = telefon;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return this.cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getTelefon() {
        return this.telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    
    
}
