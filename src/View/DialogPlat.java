package View;

import Model.Plat;

import javax.swing.*;

public class DialogPlat {
    private String info;

    /**
     * Constructor amb parametres
     *
     * @param plat per saber de quin plat es tracta
     */
    public DialogPlat(Plat plat) {
        info = JOptionPane.showInputDialog(null, "Quants plats de " + plat.getNomPlat() + " voldras?", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Comprova que les dades introduides siguin correctes
     *
     * @return true/false
     */
    public boolean comprova() {
        try {
            if (info != null) {
                int i = Integer.parseInt(info);
                if (i < 0) {
                    return false;
                }
            }else{
                info = "0";
            }
        } catch (NumberFormatException nfe) {
            System.out.println("AAAA");
            return false;

        }
        return true;
    }

    /**
     * Retorna la quantitat de plats que es vol
     *
     * @return numero de plats
     */
    public int returninfo() {
        return Integer.parseInt(info);
    }

}
