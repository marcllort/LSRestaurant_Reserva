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

    /**
     * Constructor amb parámetres
     *
     * @param plat    Plat amb les unitats desitjades
     * @param numPlat de quin plat es tracta
     */
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



    /**
     * Registra el controlador als botons del panell
     *
     * @param c controlador de la finestra que edita la comanda
     */
    public void registerController(ActionListener c) {
        jbElimina.addActionListener(c);
        jbElimina.setActionCommand("ELIMINA-" + numPlat);
        jbActualitza.addActionListener(c);
        jbActualitza.setActionCommand("ACTUALITZA-" + numPlat);
    }

    /**
     * Getter del numero de plat en qüestio
     *
     * @return numero de plat
     */
    public int getNumPlat() {
        return numPlat;
    }

    /**
     * Getter del plat que es troba al panell
     *
     * @return el plat
     */
    public Plat getPlat() {
        return plat.getPlat();
    }

    /**
     * Setter del numero del plat
     *
     * @param numPlat el numero del plat
     */
    public void setNumPlat(int numPlat) {
        this.numPlat = numPlat;
    }

    /**
     * Setter de les unitats que es volen d'aquest plat
     *
     * @param unitats unitats del plat
     */
    public void setNumUnitats(int unitats) {
        this.unitats = unitats;
    }

    /**
     * Getter de les unitats que ha introduït l'usuari a l'hora de actualitzar
     *
     * @return el valor del JTextField on es troben les unitats
     */
    public int getNovesUnitats() {
        return Integer.parseInt(jtfUnitats.getText());
    }

    /**
     * Getter de les unitats actuals d'aquell plat
     *
     * @return numero d'unitats
     */
    public int getUnitats() {
        return unitats;
    }

    /**
     * Getter del JButton d'eliminar el plat
     *
     * @return JButton
     */
    public JButton getJbElimina() {
        return jbElimina;
    }

    /**
     * Getter del boto d'actualitzacio d'unitats
     *
     * @return JButton
     */
    public JButton getJbActualitza() {
        return jbActualitza;
    }

}
