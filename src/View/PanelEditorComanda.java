package View;

import Model.Plat;
import Model.PlatComanda;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel jlUnitats = new JLabel("Unitats: ");

        JPanel jp2 = new JPanel();
        panel.add(jp2);
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        jp2.add(jlUnitats);

        jtfUnitats = new JTextField(String.valueOf(plat.getUnitats()));
        jp2.add(jtfUnitats);
        jtfUnitats.setColumns(10);
        jbActualitza = new JButton("Actualitza");
        BufferedImage buttonIcon = null;
        try {
            buttonIcon = ImageIO.read(new File(System.getProperty("user.dir") + "/Data/cross.png"));
            jbElimina = new JButton(new ImageIcon(buttonIcon));
            jbElimina.setBorder(BorderFactory.createEmptyBorder());
            jbElimina.setContentAreaFilled(false);

        } catch (IOException e) {
            e.printStackTrace();
        }

        jbElimina.setBorderPainted(true);

        JPanel jp1 = new JPanel();
        panel.add(jp1);
        jp1.setLayout(new BoxLayout(jp1, BoxLayout.X_AXIS));
        jp1.add(jbActualitza);
        jp1.add(jbElimina);

    }

    public void registerController(ActionListener c) {
        jbElimina.addActionListener(c);
        jbElimina.setActionCommand("ELIMINA-" + numPlat);
        jbActualitza.addActionListener(c);
        jbActualitza.setActionCommand("ACTUALITZA-" + numPlat);
    }

    public int getNumPlat() {
        return numPlat;
    }

    public Plat getPlat() {
        return plat.getPlat();
    }

    public void setNumPlat(int numPlat) {
        this.numPlat = numPlat;
    }

    public void setNumUnitats(int unitats) {
        this.unitats = unitats;
    }

    public int getNovesUnitats() {
        return Integer.parseInt(jtfUnitats.getText());
    }

    public int getUnitats() {
        return unitats;
    }

    public JButton getJbElimina() {
        return jbElimina;
    }

    public JButton getJbActualitza() {
        return jbActualitza;
    }
}
