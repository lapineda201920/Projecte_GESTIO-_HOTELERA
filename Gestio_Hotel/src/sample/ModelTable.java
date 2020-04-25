package sample;

public class ModelTable {

    int numero, pis, preu;
    String estat, tipo;

    public int getNumero(){
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPis() {
        return pis;
    }

    public void setPis(int pis) {
        this.pis = pis;
    }

    public int getPreu() {
        return preu;
    }

    public void setPreu(int preu) {
        this.preu = preu;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ModelTable (int numero, int pis, int preu, String estat, String tipo){

        this.numero = numero;
        this.pis = pis;
        this.preu = preu;
        this.estat = estat;
        this.tipo = tipo;
    }


}
