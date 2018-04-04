package Model;

public class Plat {

    private int idPlat;
    private String nomPlat;
    private float preu;
    private int unitats;




    public void setIdPlat(int id) { this.idPlat = id; }

    public int getIdPlat() { return idPlat; }

    public void setNomPlat(String nomPlat) { this.nomPlat = nomPlat; }

    public String getNomPlat() { return nomPlat; }

    public void setPreu(float preu){ this.preu = preu; }

    public float getPreu() { return preu; }

    public int getUnitats(){ return unitats; }

    public void setUnitats(int unitats){ this.unitats = unitats; }

    public void modificaUnitats(int quants){ unitats-= quants; }





}
