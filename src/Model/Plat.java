package Model;

import java.io.Serializable;

public class Plat implements Serializable {


    private static final long serialVersionUID = 12345L;
    private String nomPlat;
    private float preu;                                 //Al final no ens cal les unitats, enviarem els plats repetits i ja esta


    public Plat(String nom, float preu) {
        this.nomPlat = nom;
        this.preu = preu;
    }

    public String getNomPlat() {
        return nomPlat;
    }                        //Getter d'nomPlat

    public float getPreu() {
        return preu;
    }


    @Override
    public String toString() {
        return nomPlat + " - " + preu;
    }


    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }
}

