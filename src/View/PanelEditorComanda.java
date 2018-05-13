package View;

import Model.Plat;
import Model.PlatComanda;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelEditorComanda extends JPanel {

    private int numPlat;
    private int unitats;
    private PlatComanda plat;
    private JTextField jtfUnitats;
    private JButton jbElimina;
    private JButton jbActualitza;

    public PanelEditorComanda(PlatComanda plat, int numPlat) {
        this.plat = plat;
        this.numPlat = numPlat;
        this.unitats = this.plat.getUnitats();
        TitledBorder title = BorderFactory.createTitledBorder(plat.getNomPlat() + " " + plat.getPlat().getPreu() * unitats + "€");
        this.setBorder(title);
        jtfUnitats = new JTextField(String.valueOf(plat.getUnitats()));
        JLabel jlUnitats = new JLabel("Unitats: ");
        jbActualitza = new JButton("Actualitza");
        jbElimina = new JButton("X");
        jbElimina.setBorderPainted(true);

        JPanel jp1 = new JPanel();
        jp1.setLayout(new BoxLayout(jp1, BoxLayout.PAGE_AXIS));
        jp1.add(jbActualitza);
        jp1.add(jbElimina);

        JPanel jp2 = new JPanel();
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.LINE_AXIS));
        jp2.add(jlUnitats);
        jp2.add(jtfUnitats).setSize(new Dimension(4, 4));

        this.setLayout(new GridLayout(1,2));
        this.add(jp2);
        this.add(jp1);


    }

    public void registerController(ActionListener c) {
        jbElimina.addActionListener(c);
        jbElimina.setActionCommand("ELIMINA-" + numPlat);
        jbActualitza.addActionListener(c);
        jbActualitza.setActionCommand("ACTUALITZA-" + numPlat);
        System.out.println(numPlat);
    }

    public int getNumPlat() {
        return numPlat;
    }

    public Plat getPlat() {
        return plat.getPlat();
    }

    public void setNumPlat(int numPlat){
        this.numPlat = numPlat;
    }

    public void setNumUnitats(int unitats){ this.unitats = unitats;}

    public int getNovesUnitats(){return Integer.parseInt(jtfUnitats.getText());}

    public int getUnitats(){return unitats;}

    public JButton getJbElimina() { return jbElimina; }

    public JButton getJbActualitza(){return jbActualitza;}
}
