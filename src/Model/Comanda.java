package Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;

public class Comanda {

    private String usuari;              //cal enviar el usuari que ha fet la comanda

    private ArrayList<Plat> plats;      //cal que sigui el array dels plats que ha demanat la taula
  //  private boolean servit;
   // private Date data;                  //  quan fem una comanda cal enviar a la hora que sha fet


    public Comanda(String usuari){
        this.usuari = usuari;
        this.plats = new ArrayList<Plat>();
    }

    //public boolean esServit(){return servit;}

    public ArrayList<Plat> getPlats(){return plats;}

    public void addPlat(Plat plat){
        plats.add(plat);
    }

    public Plat getPlat(int i){
        return plats.get(i);
    }

  /**  public Date getData() { return data; }

    public void setData(Date data) {
        this.data = data;
    }

    public void setUsuari(String usuari){this.usuari = usuari;}
*/

}
