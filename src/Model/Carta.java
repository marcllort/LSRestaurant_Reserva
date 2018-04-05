package Model;

import java.util.ArrayList;


public class Carta {

    private ArrayList<Plat> plats;

    /**
     * Constructor que crea la carta
     */

    public Carta(){
        this.plats = new ArrayList<Plat>();
    }

    public int getNumPlats(){ return plats.size();}

    public Plat getPlat(int i){
        return plats.get(i);
    }
}
