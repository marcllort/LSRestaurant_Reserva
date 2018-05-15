package View;

import Model.Plat;

import javax.swing.*;

public class DialogPlat {
    private String info;

    public DialogPlat(Plat plat) {
        info = JOptionPane.showInputDialog(null, "Quants plats de " + plat.getNomPlat() + " voldras?", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean comprova() {
        try {
            int i = Integer.parseInt(info);
            if (i < 0) {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public int returninfo() {
        return Integer.parseInt(info);
    }

}
