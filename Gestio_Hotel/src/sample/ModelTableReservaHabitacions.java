package sample;

import java.util.Date;

public class ModelTableReservaHabitacions  {

    int id_reserva, id_habitacio, cost;
    String client, tipo, estat, hora_entrada, hora_sortida;
    Date d_entrada, d_sortida;



    public ModelTableReservaHabitacions (int id_reserva, int id_habitacio, String client, String tipo, String estat, int cost, Date d_entrada, String hora_entrada, Date d_sortida, String hora_sortida){

        this.id_reserva = id_reserva;
        this.id_habitacio = id_habitacio;
        this.client = client;
        this.tipo = tipo;
        this.estat = estat;
        this.cost = cost;
        this.d_entrada = d_entrada;
        this.hora_entrada = hora_entrada;
        this.d_sortida = d_sortida;
        this.hora_sortida = hora_sortida;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_habitacio() {
        return id_habitacio;
    }

    public void setId_habitacio(int id_habitacio) {
        this.id_habitacio = id_habitacio;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_sortida() {
        return hora_sortida;
    }

    public void setHora_sortida(String hora_sortida) {
        this.hora_sortida = hora_sortida;
    }

    public Date getD_entrada() {
        return d_entrada;
    }

    public void setD_entrada(Date d_entrada) {
        this.d_entrada = d_entrada;
    }

    public Date getD_sortida() {
        return d_sortida;
    }

    public void setD_sortida(Date d_sortida) {
        this.d_sortida = d_sortida;
    }
}
