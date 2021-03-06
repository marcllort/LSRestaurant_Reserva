package Model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Classe que conte els plats de la comanda, el usuari, la data i hora
 * Implementa Serializable per poder enviar-la per el servidor
 */
public class Comanda implements Serializable {

    private String usuari;              //cal enviar el usuari que ha fet la comanda
    private ArrayList<Plat> plats;      //cal que sigui el array dels plats que ha demanat la taula
    private Date data;                  //  quan fem una comanda cal enviar a la hora que sha fet
    private Time hora;

    /**
     * Constructor sense parametres.
     * Crea un llistat de plats buit.
     */
    public Comanda() {
        this.plats = new ArrayList<Plat>();
    }

    /**
     * Constructor amb tots els parametres per crear una comanda ja inicialitzada
     *
     * @param plats  llista de plats de la comanda
     * @param data   data de la comanda
     * @param hora   hora de la comanda
     * @param usuari l'usuari de la comanda
     */
    public Comanda(ArrayList<Plat> plats, Date data, Time hora, String usuari) {
        this.plats = plats;
        this.data = data;
        this.hora = hora;
        this.usuari = usuari;
    }

    /**
     * Getter de plats de la comanda
     *
     * @return tots els plats
     */
    public ArrayList<Plat> getPlats() {
        return plats;
    }

    /**
     * Getter de usuari de la comanda
     *
     * @return usuari de la comanda
     */
    public String getUsuari() {
        return usuari;
    }

    /**
     * Afegir plat a la comanda
     *
     * @param plat Plat a afegir
     */
    public void addPlat(Plat plat) {
        plats.add(plat);
    }

    /**
     * Getter de un plat especific
     *
     * @param i index del plat
     * @return plat seleccionat
     */
    public Plat getPlat(int i) {
        return plats.get(i);
    }

    /**
     * Getter de la data de la comanda
     *
     * @return data
     */
    public Date getData() {
        return data;
    }

    /**
     * Setter de la data de la comanda
     *
     * @param data data de la comanda
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Getter de la hora de la comanda
     *
     * @return hora
     */
    public Time getHora() {
        return hora;
    }

    /**
     * Setter de la hora de la comanda
     *
     * @param hora hora a la que es realitza la nova comanda
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }

    /**
     * Setter de usuari
     *
     * @param usuari Usuari de la comanda
     */
    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }
}