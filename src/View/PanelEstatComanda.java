package View;

import Model.Comanda;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class PanelEstatComanda extends JPanel {

    //Ens la dona la BBDD
    private Comanda comanda;
    private JScrollPane jspComanda;
    private JPanel jpComanda;

    /**
     * Constructor amb parametres. crea el panell que mostra l'estat de la comanda
     *
     * @param comanda la comanda de la reserva
     */

    public PanelEstatComanda(Comanda comanda) {

        this.comanda = comanda;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Estat de la teva comanda"));

        jpComanda = new JPanel();
        jpComanda.setLayout(new GridLayout(comanda.getPlats().size(), 1));
        JTextArea jtaAux;
        JTextArea jtaAux2;
        JPanel auxPanel;

        for (Plat p : comanda.getPlats()) {
            jtaAux2 = new JTextArea();
            jtaAux2.setEditable(false);
            if (p.isServit()) {
                jtaAux2.setText("Servit");
                jtaAux2.setFont(jtaAux2.getFont().deriveFont(Font.BOLD));
            } else {
                jtaAux2.setText("Pendent...");
            }

            auxPanel = new JPanel();
            auxPanel.setLayout(new GridLayout(1, 2));
            jtaAux = new JTextArea();
            jtaAux.setEditable(false);
            jtaAux.setText(p.getNomPlat() + " \n" + p.getPreu() + "€");
            auxPanel.add(jtaAux);
            auxPanel.add(jtaAux2);
            jpComanda.add(auxPanel);
        }
        jspComanda = new JScrollPane(jpComanda);
        this.add(jspComanda, BorderLayout.CENTER);
    }

    public void actualitzaComanda(Comanda comanda) {
        this.comanda = comanda;
        jpComanda = new JPanel();
        jpComanda.setLayout(new GridLayout(comanda.getPlats().size(), 1));
        JTextArea jtaAux;
        JTextArea jtaAux2;
        JPanel auxPanel;

        for (Plat p : comanda.getPlats()) {
            jtaAux2 = new JTextArea();
            jtaAux2.setEditable(false);
            if (p.isServit()) {
                jtaAux2.setText("Servit");
                jtaAux2.setFont(jtaAux2.getFont().deriveFont(Font.BOLD));
            } else {
                jtaAux2.setText("Pendent...");
            }

            auxPanel = new JPanel();
            auxPanel.setLayout(new GridLayout(1, 2));
            jtaAux = new JTextArea();
            jtaAux.setEditable(false);
            jtaAux.setText(p.getNomPlat() + " \n" + p.getPreu() + "€");
            auxPanel.add(jtaAux);
            auxPanel.add(jtaAux2);
            jpComanda.add(auxPanel);
        }
        jspComanda = new JScrollPane(jpComanda);
        this.add(jspComanda, BorderLayout.CENTER);

    }


}
