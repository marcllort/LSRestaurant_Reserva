package Model;

public class PlatComanda {

    private Plat plat;
    private int unitats;

    public PlatComanda(Plat plat) {
        this.plat = plat;
        unitats = 0;

    }

    public void augmentaUnitats() {
        unitats++;
    }

    public String getNomPlat() {
        return plat.getNomPlat();
    }

    public int getUnitats() {
        return unitats;
    }

    public Plat getPlat() {
        return plat;
    }
}
