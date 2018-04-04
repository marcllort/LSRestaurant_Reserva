package View;

import javax.swing.*;
import java.awt.*;

public class PanelAcces extends JPanel {


    private JLabel jlUsuari;
    private JLabel jlContrasenya;
    private JTextField jtfUsuari;
    private JTextField jtfContrasenya;
    private JButton jbEnvia;

    /**
     * Constructor sense parametres.
     * Crea el panell per accedir a la reserva
     */
    public PanelAcces(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("LSRestaurant"));
        JPanel jpUsuari = new JPanel();
        JPanel jpContrasenya = new JPanel();
        ((FlowLayout)jpUsuari.getLayout()).setAlignment(FlowLayout.LEFT);
        jlUsuari = new JLabel("Nom d'usuari");
        jtfUsuari = new JTextField();
        jtfUsuari.setPreferredSize(new Dimension(280, 25));
        jpUsuari.add(jlUsuari);
        jpUsuari.add(jtfUsuari);
        ((FlowLayout)jpContrasenya.getLayout()).setAlignment(FlowLayout.LEFT);
        jlContrasenya = new JLabel("Contrasenya");
        jtfContrasenya = new JTextField();
        jtfContrasenya.setPreferredSize( new Dimension(300, 25));
        jbEnvia = new JButton("Envia");
        this.add(jpUsuari);
        this.add(jpContrasenya);
        this.add(jbEnvia);


    }




}
