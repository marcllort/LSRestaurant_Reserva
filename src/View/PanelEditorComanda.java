package View;

import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelEditorComanda extends JPanel {

    private int numPlat;
    private Plat plat;
    private JLabel jlNomPlat;
    private JLabel jlPreuPlat;
    private JButton jbElimina;

    public PanelEditorComanda(Plat plat, int numPlat) {
        this.plat = plat;
        this.numPlat = numPlat;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        jlNomPlat = new JLabel(plat.getNomPlat());
        jlPreuPlat = new JLabel(String.valueOf(plat.getPreu()));
        jbElimina = new JButton("X");
        jbElimina.setFont(jbElimina.getFont().deriveFont(Font.BOLD));

        this.add(jlNomPlat);
        this.add(jlPreuPlat);
        this.add(jbElimina);

    }

    public void registerController(ActionListener c) {
        jbElimina.addActionListener(c);
        jbElimina.setActionCommand("ELIMINA-" + numPlat);
        System.out.println(numPlat);
    }

    public int getNumPlat() {
        return numPlat;
    }

    public Plat getPlat() {
        return plat;
    }

}
