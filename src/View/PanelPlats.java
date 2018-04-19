package View;

import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelPlats extends JPanel {

    private ArrayList<Plat> plats;
    private int numPagina;
    private JRadioButton jrbPlat1;      //cambiar a arraylis de btons
    private JRadioButton jrbPlat2;
    private JRadioButton jrbPlat3;
    private JRadioButton jrbPlat4;
    private JRadioButton jrbPlat5;
    private JRadioButton jrbPlat6;

    public PanelPlats(ArrayList<Plat> plats, int numPagina) {
        this.plats = plats;
        this.numPagina = numPagina;
        this.setLayout(new GridLayout(2, 3));
        afageixPlats();
    }

    private void afageixPlats() {

        int i = 0;
        jrbPlat1 = new JRadioButton(plats.get(i).getNomPlat() + "\n" + plats.get(i).getPreu());
        this.add(jrbPlat1);
        i++;

        if (i < plats.size()) {
            jrbPlat2 = new JRadioButton(plats.get(i).getNomPlat() + "\n" + plats.get(i).getPreu());
            this.add(jrbPlat2);
            i++;
            if (i < plats.size()) {
                jrbPlat3 = new JRadioButton(plats.get(i).getNomPlat() + "\n" + plats.get(i).getPreu());
                this.add(jrbPlat3);
                i++;
                if (i < plats.size()) {
                    jrbPlat4 = new JRadioButton(plats.get(i).getNomPlat() + "\n" + plats.get(i).getPreu());
                    this.add(jrbPlat4);
                    i++;
                    if (i < plats.size()) {
                        jrbPlat5 = new JRadioButton(plats.get(i).getNomPlat() + "\n" + plats.get(i).getPreu());
                        this.add(jrbPlat5);
                        i++;
                        if (i < plats.size()) {
                            jrbPlat6 = new JRadioButton(plats.get(i).getNomPlat() + "\n" + plats.get(i).getPreu());
                            this.add(jrbPlat6);
                        }
                    }
                }
            }
        }
    }

    public void registerController(ActionListener controlador) {

        int i = 0;
        jrbPlat1.addActionListener(controlador);
        jrbPlat1.setActionCommand("PLAT1-" + numPagina);
        i++;

        if (i < plats.size()) {
            jrbPlat2.addActionListener(controlador);
            jrbPlat2.setActionCommand("PLAT2-" + numPagina);
            i++;
            if (i < plats.size()) {
                jrbPlat2.addActionListener(controlador);
                jrbPlat2.setActionCommand("PLAT3-" + numPagina);
                i++;
                if (i < plats.size()) {
                    jrbPlat2.addActionListener(controlador);
                    jrbPlat2.setActionCommand("PLAT4-" + numPagina);
                    i++;
                    if (i < plats.size()) {
                        jrbPlat2.addActionListener(controlador);
                        jrbPlat2.setActionCommand("PLAT5-" + numPagina);
                        i++;
                        if (i < plats.size()) {
                            jrbPlat2.addActionListener(controlador);
                            jrbPlat2.setActionCommand("PLAT6-" + numPagina);
                        }
                    }
                }
            }
        }
    }

    public int getNunPagina() {
        return numPagina;
    }

    public Plat getPlat(int i) {
        return plats.get(i);
    }

}
