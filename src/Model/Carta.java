package Model;

import java.util.LinkedList;

public class Carta {

    private LinkedList<Plat> plats;

    /**
     * Constructor que omple la carta
     */

    public Carta(LinkedList<Plat> plats){
        this.plats = plats;
    }

    public int getNumPlats(){ return plats.size();}

    public Plat getPlat(int i){
        return plats.get(i);
    }
}
