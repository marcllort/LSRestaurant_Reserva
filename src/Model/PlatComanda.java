package Model;

public class PlatComanda {

    private Plat plat;
    private int unitats;

    /**
     * Contructor amb parametres
     * @param plat plat que es vol guardar
     */
    public PlatComanda(Plat plat) {
        this.plat = plat;
        unitats = 0;

    }

    /**
     * Augmenta en un el numero d'unitats d'aquell plat
     */
    public void augmentaUnitats() {
        unitats++;
    }

    /**
     * Getter del nom del plat
     * @return nom del plat
     */
    public String getNomPlat() {
        return plat.getNomPlat();
    }

    /**
     * Getter del numero d'unitats d'aquell plat
     * @return unitats
     */
    public int getUnitats() {
        return unitats;
    }

    /**
     * Getter del plat
     * @return plat
     */
    public Plat getPlat() {
        return plat;
    }
}
